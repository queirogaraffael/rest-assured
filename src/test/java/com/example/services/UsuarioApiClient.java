package com.example.services;


import com.example.entities.usuario.UserRole;
import com.example.entities.usuario.UsuarioCreateDTO;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UsuarioApiClient {

    private Response response;
    private UsuarioCreateDTO usuarioCreateDTO;
    private Faker faker;
    private String lastGeneratedId;

    public UsuarioApiClient() {
        faker = new Faker();
    }

    public UsuarioCreateDTO gerarDadosUsuario() {
        usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setNome(faker.name().name());
        usuarioCreateDTO.setEmail(faker.internet().emailAddress());
        usuarioCreateDTO.setPassword("123456");
        usuarioCreateDTO.setAdministrador(UserRole.USER.getApiValue());

        return usuarioCreateDTO;
    }

    public Response criarNovoUsuario(String url, UsuarioCreateDTO payload) {
        response = given()
                .header("Content-type", "application/json")
                .and()
                .log().all()
                .body(payload)
                .when()
                .post(url)
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    public String validarDadosUsuarioCadastrado(Response response, int statusCode) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        assertEquals(statusCode, response.getStatusCode());
        assertEquals("Cadastro realizado com sucesso", jsonPathEvaluator.get("message"));

        String id = jsonPathEvaluator.get("_id");
        assertNotNull("O ID do usuário não deve ser nulo", id);
        lastGeneratedId = id;
        return id;
    }

    public String verificarUsuarioExistente(String idParaBuscar) {
        if (idParaBuscar == null || idParaBuscar.isEmpty()) {
            UsuarioCreateDTO novoUsuarioData = gerarDadosUsuario();
            Response novoResponse = criarNovoUsuario("https://serverest.dev/usuarios", novoUsuarioData);
            return validarDadosUsuarioCadastrado(novoResponse, 201);
        }
        lastGeneratedId = idParaBuscar;
        return idParaBuscar;
    }


    public Response consultarUsuario(String url, String id) {
        response = given()
                .header("Content-type", "application/json")
                .and()
                .log().all()
                .when()
                .get(url + "/" + id)
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    public void validarDadosUsuarioConsultado(Response response, String expectedId, int statusCode) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        assertEquals(statusCode, response.getStatusCode());
        if (statusCode == 200) {
            assertEquals(expectedId, jsonPathEvaluator.get("_id"));
            assertEquals(usuarioCreateDTO.getNome(), jsonPathEvaluator.get("nome"));
            assertEquals(usuarioCreateDTO.getEmail(), jsonPathEvaluator.get("email"));
            assertEquals(usuarioCreateDTO.getAdministrador(), String.valueOf(jsonPathEvaluator.getBoolean("administrador")));

        }
    }

    public void validarDadosUsuarioNaoCadastrado(Response response, int statusCode) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        assertEquals(statusCode, response.getStatusCode());

        if (statusCode == 400) {
            assertEquals("id deve ter exatamente 16 caracteres alfanuméricos", jsonPathEvaluator.get("id"));
        } else if (statusCode == 404) {
            assertEquals("Usuário não encontrado", jsonPathEvaluator.get("message"));
        }
    }

    public String gerarIdAlfanumericoValido() {
        return RandomStringUtils.randomAlphanumeric(16);
    }
}