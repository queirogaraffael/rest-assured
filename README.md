# Projeto de Testes Automatizados em Java

Este projeto foi desenvolvido por mim, Raffael Queiroga, com base no vídeo tutorial [Test Automation in Java | BDD with Cucumber and Gherkin | API Automation](https://www.youtube.com/watch?v=DSyBpV_1SZ4), utilizando o StackSpot AI para auxiliar na adaptação e melhorias.

## Tecnologias Utilizadas
- **Java 8**: Linguagem de programação para o desenvolvimento dos testes.
- **Spring Boot 2.7**: Framework para simplificar o desenvolvimento de aplicações Java, responsável por gerenciar o ciclo de vida, injeção de dependências e configuração automática do projeto.
- **JUnit**: Framework para testes unitários.
- **Cucumber**: Ferramenta de BDD (Behavior Driven Development) para escrita dos testes.
- **Rest Assured**: Biblioteca para automação de testes de APIs RESTful.
- **Gherkin**: Linguagem para descrever cenários de testes de forma legível.

## Estrutura do Projeto
- **src/main/java**: Contém o código de produção (caso aplicável).
- **src/test/java**: Contém os testes automatizados.
- **features/**: Contém os arquivos `.feature` escritos em Gherkin.
- **TestRunner.java**: Classe responsável por executar os testes.

## Executando os Testes
Para executar os testes, basta rodar a classe `TestRunner` localizada no pacote `tests`. Isso pode ser feito diretamente pela IDE ou via terminal utilizando o Maven ou Gradle, dependendo da configuração.

**Exemplo (via IDE):**
- Navegue até o arquivo `TestRunner.java`.
- Clique com o botão direito e selecione "Run TestRunner".

## Observações
- O projeto foi adaptado para se encaixar em necessidades e ajustes específicos de aprendizado e prática de testes automatizados.
- Modificações e melhorias foram feitas com base nas práticas apresentadas no vídeo.
