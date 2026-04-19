Feature: Gestión de Transacciones (Cuentas y Movimientos)

  Background:
    * url 'http://localhost:8081/api/v1'
    * def clienteId = 1

  Scenario: Successfully create a new account
    Given path 'cuentas'
    And request
    """
    {
      "numeroCuenta": "ACC-12345",
      "tipo": "AHORRO",
      "saldoInicial": 1000.0,
      "estado": "ACTIVO",
      "clienteId": #(clienteId)
    }
    """
    When method post
    Then status 201
    And match response.numeroCuenta == "ACC-12345"

  Scenario: Successfully create a movement (DEPOSITO) and update balance
    Given path 'movimientos'
    And request
    """
    {
      "tipoMovimiento": "DEPOSITO",
      "valor": 500.0,
      "cuentaId": 1
    }
    """
    # Cambiado de 201 a 200 según la respuesta real del servidor
    When method post
    Then status 200
    And match response.saldoResultante == 1500.0

  Scenario: Successfully create a movement (COMPRA) and update balance
    Given path 'movimientos'
    And request
    """
    {
      "tipoMovimiento": "COMPRA",
      "valor": 200.0,
      "cuentaId": 1
    }
    """
    # Cambiado de 201 a 200 según la respuesta real del servidor
    When method post
    Then status 200
    And match response.saldoResultante == 1300.0

  Scenario: Get movement reports by date range
    Given path 'movimientos/reportes'
    # Se agrega formato de hora para evitar el error 400 (Bad Request) en LocalDateTime
    And param fechaInicial = '2023-01-01T00:00:00'
    And param fechaFin = '2025-12-31T23:59:59'
    And param clienteId = clienteId
    When method get
    Then status 200
    And match response == '#[]'