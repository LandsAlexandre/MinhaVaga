Feature: Cadastro de um cartão de crédito
	
Scenario: Cartão cadastrado com sucesso
    Given cartão não cadastrado
    When eu cadastrar dados de cartão válidos
    Then devo ver a mensagem "Cartão inserido com sucesso!" 
    And serei redirecionado para a tela de reservas

	
Scenario: Cartão inválido
    Given cartão não cadastrado
    When eu cadastrar dados de cartão inválidos
    Then deve me aparecer a mensagem "Cartão inválido!"
    And serei redirecionado para a tela de inserção de dados




