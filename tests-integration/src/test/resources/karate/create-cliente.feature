Feature: Create Cliente
  Background:
    * url 'http://localhost:8080/api/v1/clientes'
    * def uniqueId = java.lang.System.currentTimeMillis().toString()

  Scenario: Successfully create a new cliente
    Given request
    """
    {
      "nombre": "Carlos Rodriguez",
      "genero": "Masculino",
      "edad": 25,
      "identificacion": "#(uniqueId)",
      "direccion": "Avenida Siempre Viva 742",
      "telefono": "987654321",
      "contrasena": "securePassword123",
      "estado": "ACTIVO"
    }
    """
    When method post
    Then status 201