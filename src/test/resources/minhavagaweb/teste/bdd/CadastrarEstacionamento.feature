Feature: gerenciar dados de um estacionamento

Scenario: Dados cadastrados com sucesso
	Given estacionamento ainda não cadastrado
	When eu cadastrar dados validos
	Then eu verei a mensagem "Estacionamento cadastrado!"

Scenario: Dados atualizados com sucesso
	Given um estacionamento cadastrado
	When eu atualizar seus dados corretamente
	Then eu contemplarei a mensagem "Estacionamento atualizado!"
	
Scenario: Dados deletados com sucesso
	Given um ja estacionamento cadastrado
	When eu deletar este estacionamento
	Then eu visualizarei a mensagem "Estacionamento deletado!"