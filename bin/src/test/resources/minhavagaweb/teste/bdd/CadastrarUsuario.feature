Feature: Cadastro de um novo usuário.
O cadastro deve ser simples e rápido.


Scenario: Cadastro com sucesso

Novos usuários devem receber e-mails de confirmação com uma mensagem de saudação personalizada quando se cadastram no sistema

    Given eu tenha escolhido me cadastrar
    When eu cadastrar dados válidos
    Then eu devo ver a mensagem "Registro realizado com sucesso!"
    And eu devo ver uma mensagem de saudação personalizada


Scenario: E-mail duplicado

Quando alguém tenta criar uma conta com um e-mail que já está cadastrado

    Given eu tenha escolhido me cadastrar
    When eu cadastre um e-mail que já está cadastrado
    Then eu devo ser informado que o e-mail já está registrado
    And eu devo ver a tela de recuperar minha senha

