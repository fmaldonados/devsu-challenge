Feature: Smoke Test de Microservicos

  Scenario: Verificar disponibilidad de Microservicio Cliente
    Given url clienteServiceUrl
    When method get
    Then status 200

  Scenario: Verificar disponibilidad de Microservicio Transaccion
    Given url transaccionServiceUrl
    When method get
    Then status 200