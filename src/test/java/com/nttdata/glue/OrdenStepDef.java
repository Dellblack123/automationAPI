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

    @When("envio la siguiente informacion en el cuerpo de la solicitud")
    public void envioLaSiguienteInformacionEnElCuerpoDeLaSolicitud(String cuerpoSolicitud) {
        ordenStep.crearOrdenConJson(cuerpoSolicitud);
    }

    @Then("la orden se crea exitosamente {int}")
    public void laOrdenSeCreaExitosamente(int codigo) {
        ordenStep.validarCodigoRespuesta(codigo);
    }

    @And("el campo {string} en la respuesta es {string}")
    public void elCampoEnLaRespuestaEs(String campo, String valorEsperado) {
        ordenStep.validarCampoBody(campo, valorEsperado.replaceAll("[<>]", ""));
    }

    @And("el codigo de respuesta es {int}")
    public void elCódigoDeRespuestaEs(int codigo) {
        ordenStep.validarCodigoRespuesta(codigo);
    }

    /*@Given("la url www.petstore.swagger.io con el path de la orden {string}")
    public void laUrlConPathDeOrden(String orderId) {
        ordenStep.definirURL("https://petstore.swagger.io/v2/store/order/" + orderId);
    }*/

    @When("hago la consulta de la orden {string}")
    public void hagoLaConsultaDeLaOrden(String ordenId) {
        ordenStep.consultarOrden(ordenId);
    }
}