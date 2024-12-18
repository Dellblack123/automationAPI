@todo
Feature: Creacion y consultas de ordenes en la API de Petstore

  @crearOrden
  Scenario Outline: Crear una orden usando el metodo POST en /store/order
    Given la url www.petstore.swagger.io
    When envio la siguiente informacion en el cuerpo de la solicitud
      """
      {
        "id": <id>,
        "petId": <petId>,
        "quantity": <quantity>,
        "shipDate": "<shipDate>",
        "status": "<status>",
        "complete": <complete>
      }
      """
    Then la orden se crea exitosamente <codigo>
    And el campo "status" en la respuesta es "<status>"

    Examples:
      | id    | petId | quantity | shipDate     | status  | complete | codigo |
      | 1     | 1     | 2        | 2024-12-17   | placed  | true     | 200    |
      | 2     | 2     | 2        | 2024-12-17   | shipped | false    | 200    |
      | 3     | 3     | 1        | 2024-12-17   | shipped | false    | 200    |


  @consultarOrden
  Scenario Outline: Consultar una orden especifica por ID
    Given la url www.petstore.swagger.io
    When hago la consulta de la orden "<orderId>"
    Then el codigo de respuesta es 200
      And el campo "id" en la respuesta es "<orderId>"

    Examples:
      | orderId |
      | 1       |
      | 2       |
      | 3       |
