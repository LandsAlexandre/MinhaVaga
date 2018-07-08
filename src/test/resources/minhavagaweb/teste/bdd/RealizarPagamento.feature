Feature: Realizar pagamento
Pagamento de reservas via cartao


Scenario: Pagamento aprovado pela administradora

    Given eu tenha uma reserva pendente
    And eu tenha cadastrado um cartao
    When selecionar realizar pagamento
    And a administradora de cartao aprovar o pagamento
    Then eh exibido a mensagem "Sucesso!"
    And eu devo ser redirecionado para homepage


Scenario: Pagamento reprovado pela administradora

    Given eu tenha uma reserva pendente
    And eu tenha cadastrado um cartao
    When selecionar realizar pagamento
    And a administradora de cartao reprovar o pagamento
    Then eh mostrado a mensagem "Falhou!"
    And eu devo ser redirecionado para teladepagamento