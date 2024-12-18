package com.nttdata.steps;

import com.nttdata.model.Orden;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrdenStep {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String ENDPOINT_ORDEN = "/store/order";
    private String url = "";
    private Response response;

    @Step("Definir la URL base")
    public void definirURL(String url) {
        SerenityRest.setDefaultBasePath(url);
    }

    @Step("Crear una orden con JSON")
    public void crearOrdenConJson(String body) {
        response = SerenityRest.given()
                .contentType("application/json")
                .body(body)
                .post(BASE_URL + "/store/order")
                .then()
                .extract()
                .response();
    }

    @Step("Validar el código de respuesta {0}")
    public void validarCodigoRespuesta(int codigo) {
        restAssuredThat(response -> response.statusCode(codigo));
    }

    public void consultarOrden(String orderId) {
        response = SerenityRest.given()
                .contentType("application/json")
                .log().all()
                .get(BASE_URL + ENDPOINT_ORDEN + "/" + orderId)
                .then()
                .log().all()
                .extract()
                .response();
    }


    @Step("Validar campo {0} en el body")
    public void validarCampoBody(String campo, String valorEsperado) {
        String actual = response.jsonPath().getString(campo);
        actual = actual.replaceAll("[<>]", "");
        org.hamcrest.MatcherAssert.assertThat("El valor del campo no coincide", actual, equalTo(valorEsperado));
    }



}
