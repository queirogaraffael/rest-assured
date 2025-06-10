package com.example.stepDefinitions;

import com.example.entities.usuario.UsuarioCreateDTO;
import com.example.services.UsuarioApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UsuarioSteps {

    private Response response;
    private UsuarioCreateDTO usuarioCreateDTO;
    private String idUsuario;
    private final UsuarioApiClient usuarioApiClient = new UsuarioApiClient();

    @Given("Eu tenho os dados para cadastrar um novo usuario")
    public void eu_tenho_os_dados_para_cadastrar_um_novo_usuario() {
        usuarioCreateDTO = usuarioApiClient.gerarDadosUsuario();
    }

    @When("Eu faço um pedido POST para a URL {string}")
    public void eu_faco_um_pedido_post_para_a_url(String url) {
        response = usuarioApiClient.criarNovoUsuario(url, usuarioCreateDTO);
    }

    @Then("O status do código de resposta deve ser {int}")
    public void o_status_do_codigo_de_resposta_deve_ser(int statusCode) {
        idUsuario = usuarioApiClient.validarDadosUsuarioCadastrado(response, statusCode);
    }

    @Given("Eu não tenho um usuario cadastrado mas desejo cadastrar")
    public void eu_nao_tenho_um_usuario_cadastrado_mas_desejo_cadastrar() {
        idUsuario = usuarioApiClient.verificarUsuarioExistente(null);
    }

    @When("Eu faço um pedido GET para a URL {string}")
    public void eu_faco_um_pedido_get_para_a_url(String url) {
        response = usuarioApiClient.consultarUsuario(url, idUsuario);
    }

    @Then("O status do código de resposta deve ser de {int}")
    public void o_status_do_codigo_de_resposta_deve_ser_de(int statusCode) {
        usuarioApiClient.validarDadosUsuarioConsultado(response, idUsuario, statusCode);
    }

    @Given("Eu não tenho um usuario cadastrado com um id {string}")
    public void eu_nao_tenho_um_usuario_cadastrado_com_um_id(String idParaBuscar) {
        idUsuario = idParaBuscar;
    }

    @When("Eu faço uma tentativa de pedido GET para a URL {string}")
    public void eu_faco_uma_tentativa_de_pedido_get_para_a_url(String url) {
        response = usuarioApiClient.consultarUsuario(url, idUsuario);
    }

    @Then("O status do código de resposta deverá ser de {int}")
    public void o_status_do_codigo_de_resposta_devera_ser_de(int statusCode) {
        usuarioApiClient.validarDadosUsuarioNaoCadastrado(response, statusCode);
    }
}