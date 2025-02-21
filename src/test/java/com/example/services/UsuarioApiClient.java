package com.example.services;


import com.example.entities.usuario.UserRole;
import com.example.entities.usuario.Usuario;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UsuarioApiClient {

    private Response response;
    private String requestBody;
    private String idUsuario;
    private Usuario usuario;
    private Faker faker;
    private String novoIdUsuario;

    public String gerarDadosUsuario(){
        faker = new Faker();

        usuario = new Usuario();
        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha("123456");
        usuario.setRole(UserRole.USER);

        requestBody = "{\n" +
                "  \"nome\": \""+usuario.getNome()+"\",\n" +
                "  \"email\": \""+usuario.getEmail()+"\",\n" +
                "  \"password\": \""+ usuario.getSenha()+"\",\n" +
                "  \"role\": \""+ usuario.getRole()+"\"\n" +
                "}";

        return requestBody;
    }

    public Response criarNovoUsuario(String url, String requestBody){
        response = given()
                .header("Content-type", "application/json")
                .and()
                .log().all()
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    public String validarDadosUsuarioCadastrado(Response response, int statusCode){
        JsonPath jsonPathEvaluator = response.jsonPath();

        assertEquals(statusCode, response.getStatusCode());
        assertEquals("Cadastro realizado com sucesso",jsonPathEvaluator.get("message"));

        idUsuario = jsonPathEvaluator.get("_id");

        return idUsuario;
    }

    public String verificarUsuarioExistente(String idUsuario){
        String novoBody;
        Response novoResponse;
        if (idUsuario == null){
            novoBody = gerarDadosUsuario();
            novoResponse = criarNovoUsuario("https://serverest.dev/usuarios",novoBody);
            novoIdUsuario = validarDadosUsuarioCadastrado( novoResponse, 201);
        }
        else{
            novoIdUsuario = idUsuario;
        }
        return novoIdUsuario;
    }

    public Response consultarUsuario(String url, String novoIdUsuario){
        response = given()
                .header("Content-type", "application/json")
                .and()
                .log().all()
                .when()
                .get(url + "/" + novoIdUsuario)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public void validarDadosUsuarioConsultado(Response response, String novoId, int statusCode){
        JsonPath jsonPathEvaluator = response.jsonPath();


        if (novoIdUsuario.equals(novoId)) {
            assertEquals(statusCode, response.getStatusCode());
            assertEquals(novoIdUsuario, jsonPathEvaluator.get("_id"));
        }
        else {
            assertEquals(usuario.getNome(), jsonPathEvaluator.get("nome"));
            assertEquals(usuario.getEmail(), jsonPathEvaluator.get("email"));
            assertEquals(usuario.getSenha(), jsonPathEvaluator.get("password"));
            assertEquals(usuario.getRole(), jsonPathEvaluator.get("administrador"));
            assertEquals(statusCode, response.getStatusCode());
            assertEquals(idUsuario, jsonPathEvaluator.get("_id"));
        }
    }

    public void validarDadosUsuarioNaoCadastrado(Response response, int statusCode){
        JsonPath jsonPathEvaluator = response.jsonPath();

        assertEquals("Usuário não encontrado", jsonPathEvaluator.get("message"));
        assertEquals(statusCode, response.getStatusCode());

    }
}