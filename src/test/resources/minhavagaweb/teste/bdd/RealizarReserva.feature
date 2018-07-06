Feature: Realizar uma reserva
	
Scenario: Vaga reservada com sucesso.
    Given eu não tenha pendência de pagamento
    When eu selecionar o estacionamento listado e o tipo da vaga que desejo
    Then devo ser questionado "Confimar solicitação?"
    And eu confirmar a solicitação
    Then devo ter a resposta "Reserva realizada com sucesso!"

Scenario: Cliente com pendência.
    Given eu tenha pendência de pagamento
    Then devo ser avisado com a mensagem "Você possui pendência de pagamento.. Por favor, regularize sua situação na aba Pagamentos."
    And devo ser redirecionado para a tela inicial

Scenario: Vaga indisponível
    Given eu não tenha pendência de pagamento
    When eu selecionar o estacionamento listado e o tipo da vaga que desejo
    And não houver vaga disponível
    Then devo receber a mensagem "Não há vagas disponíveis neste estacionamento"

Scenario: Cliente rejeita solicitação
    Given eu não tenha pendência de pagamento
    When eu selecionar o estacionamento listado e o tipo da vaga que desejo
    Then devo ser questionado "Confimar solicitação?"
    And eu não confirme a solicitação
    Then devo ser redirecionado para a tela inicial





