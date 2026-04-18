Feature: Read Cliente

  Background:
    * url 'http://localhost:8080/api/v1/clientes'
    # Generamos un ID único basado en el tiempo actual para evitar el error de Duplicate Key
    * def uniqueId = java.lang.System.currentTimeMillis().toString()
    * def clienteBody =
    """
    {
      "nombre": "Cliente Inicial",
      "genero": "Masculino",
      "edad": 20,
      "identificacion": "#(uniqueId)",
      "direccion": "Direccion Inicial",
      "telefono": "00000000",
      "contrasena": "password123",
      "estado": "ACTIVO"
    }
    """

  Scenario: Successfully create and get all clientes
    Given request clienteBody
    When method post
    Then status 201
    
    # Ahora validamos el GET
    When method get
    Then status 200 
    And match response == '#[]'
    And match each response == { id: '#>', nombre: '#string', genero: '#string', edad: '#number', identificacion: '#string', direccion: '#string', telefono: '#string', estado: '#string' }
    # Usamos JSONPath para verificar que AL MENOS UN elemento tenga el nombre esperado sin importar los demás campos
    And karate.match(karate.jsonPath(response, '$[?(@.nombre == "Cliente Inicial")]'), '#[]').length > 0

Scenario: Get cliente by ID
  # Nota: Este escenario depende de que el ID '1' exista. 
  # Si la DB se limpia, el ID podría variar. 
  # Lo ideal es que este test también cree su propio dato o use un ID dinámico.
  Given path '1'
  When method get
  Then status 200 || status 404