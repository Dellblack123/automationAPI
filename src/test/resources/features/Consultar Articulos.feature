Feature: Creación de órdenes en la API de Petstore

  @crearOrden
  Scenario Outline: Crear una orden usando el método POST en /store/order
    Given la url www.petstore.swagger.io
    When envío la siguiente información en el cuerpo de la solicitud
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
    Then la orden se crea exitosamente
    And el código de respuesta es <codigo>
    And el campo "status" en la respuesta es "<status>"

    Examples:
      | id    | petId | quantity | shipDate               | status  | complete | codigo |
      | 1 | 1 | 2        | 2024-12-17   | placed  | true     | 200    |
      | 2 | 2 | 2        | 2024-12-17   | shipped | false    | 200    |


  @consultarOrden
  Scenario Outline: Consultar una orden específica por ID
    Given la url www.petstore.swagger.io con el path de la orden "<orderId>"
    When hago la consulta de la orden
    Then imprimo la orden

    Examples:
      | orderId |
      | 1       |
      | 2       |
      | 3       |
