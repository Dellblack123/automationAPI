package com.nttdata.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrdenStep {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String ENDPOINT_ORDEN = "/store/order";
    private Response response;

    @Step("Definir la URL base")
    public void definirURL(String url) {
        SerenityRest.setDefaultBasePath(url);
    }

    @Step("Enviar una orden con JSON")
    public void crearOrdenConJson(String body) {
        response = SerenityRest.given()
                .contentType("application/json")
                .body(body)
                .log().all()
                .post(BASE_URL + ENDPOINT_ORDEN)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("Validar el código de respuesta {0}")
    public void validarCodigoRespuesta(int codigo) {
        restAssuredThat(response -> response.statusCode(codigo));
    }

    @Step("Validar el campo status con valor {0}")
    public void validarCampoStatus(String statusEsperado) {
        restAssuredThat(response -> response.body("status", equalTo(statusEsperado)));
    }
}
