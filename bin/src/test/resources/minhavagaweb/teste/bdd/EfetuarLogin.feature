Feature: Login no sistema

Scenario: Login efetuado com sucesso.
    Given usuário cadastrado
    When eu tentar logar com dados válidos
    Then devo ver a mensagem de saudação personalizada

Scenario: Login ou senha incorretos.
    Given usuário cadastrado
    When eu logar com dados inválidos
    Then devo ver o texto "Login ou senha incorretos"

Scenario: Usuário não cadastrado
    Given usuário não cadastrado
    When eu tentar logar dados que não existem no sistema
    Then eu devo ver o texto "Usuário não cadastrado"
    And eu devo ser redirecionado para a tela de cadastro
