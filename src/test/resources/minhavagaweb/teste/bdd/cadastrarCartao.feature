Feature: Cadastro de um cartão de crédito
	
Scenario: Cartão cadastrado com sucesso
    Given eu tenha escolhido inserir um novo cartão
    When eu cadastrar dados válidos
    Then devo ver a mensagem "Cartão inserido com sucesso!" 
    And serei redirecionado para a tela de reservas

	
Scenario: Cartão inválido
    Given eu tenha escolhido inserir um novo cartão
    When eu cadastrar dados inválidos
    Then deve me aparecer a mensagem "Cartão inválido!"
    And serei redirecionado para a tela de inserção de dados




