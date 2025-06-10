Feature: Usuário API

  @todos @usuario-cadastrar
  Scenario: Deve cadastrar novo usuário
    Given Eu tenho os dados para cadastrar um novo usuario
    When Eu faço um pedido POST para a URL "https://serverest.dev/usuarios"
    Then O status do código de resposta deve ser 201

  @todos @usuario-consultar-cadastrado
  Scenario: Deve consultar usuário cadastrado
    Given Eu não tenho um usuario cadastrado mas desejo cadastrar
    When Eu faço um pedido GET para a URL "https://serverest.dev/usuarios"
    Then O status do código de resposta deve ser de 200

  @todos @usuario-consultar-nao-existente-id-invalido
  Scenario: Deve consultar usuário com ID inválido
    Given Eu não tenho um usuario cadastrado com um id "XPTO"
    When Eu faço uma tentativa de pedido GET para a URL "https://serverest.dev/usuarios"
    Then O status do código de resposta deverá ser de 400

  @todos @usuario-consultar-nao-existente-id-valido
  Scenario: Deve consultar usuário com ID válido mas não existente
    Given Eu não tenho um usuario cadastrado com um id "naoExisteEsseId1"
    When Eu faço uma tentativa de pedido GET para a URL "https://serverest.dev/usuarios"
    Then O status do código de resposta deverá ser de 404