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

Scenario: Atualizando um cartao
	Given eu tenha um cartao cadastrado
	When eu atualizar seus dados
	Then eu deverei ver a mensagem "Atualização completa!"

Scenario: Deletando um cartao
	Given eu ja tenha um cartao cadastrado
	When eu deletar este cartao
	Then ele nao estara mais guardado


