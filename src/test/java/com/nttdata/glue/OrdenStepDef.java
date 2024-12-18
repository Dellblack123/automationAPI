package com.nttdata.glue;

import com.nttdata.steps.OrdenStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrdenStepDef {

    private final OrdenStep ordenStep = new OrdenStep();

    @Given("la url www.petstore.swagger.io")
    public void laUrl() {
        ordenStep.definirURL("https://petstore.swagger.io/v2");
    }

    @When("envío la siguiente información en el cuerpo de la solicitud")
    public void envíoLaSiguienteInformaciónEnElCuerpoDeLaSolicitud(String cuerpoSolicitud) {
        System.out.println("Cuerpo de la solicitud: " + cuerpoSolicitud);

        String body = cuerpoSolicitud
                .replace("<id>", "1")
                .replace("<petId>", "1")
                .replace("<quantity>", "2")
                .replace("<shipDate>", "2024-12-17")
                .replace("<status>", "placed")
                .replace("<complete>", "true");

        ordenStep.crearOrdenConJson(body);
    }

    @Then("la orden se crea exitosamente")
    public void laOrdenSeCreaExitosamente() {
        System.out.println("Orden creada exitosamente.");
    }

    @And("el campo {string} en la respuesta es {string}")
    public void elCampoEnLaRespuestaEs(String campo, String valorEsperado) {
        if ("status".equals(campo)) {
            ordenStep.validarCampoStatus(valorEsperado);
        }
    }

    @And("el código de respuesta es {int}")
    public void elCódigoDeRespuestaEs(int codigo) {
        ordenStep.validarCodigoRespuesta(codigo);
    }

    @Given("la url www.petstore.swagger.io con el path de la orden {string}")
    public void laUrlConPathDeOrden(String orderId) {
        ordenStep.definirURL("https://petstore.swagger.io/v2/store/order/" + orderId);
    }

    @When("hago la consulta de la orden")
    public void hagoLaConsultaDeLaOrden() {
        ordenStep.consultarOrden();
    }

    @Then("imprimo la orden")
    public void imprimoLaOrden() {
        ordenStep.imprimirOrden();
    }

}
