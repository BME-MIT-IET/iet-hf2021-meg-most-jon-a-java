import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_EszkimoFelveszBuvarruha {
    @Given("a Tabla with a Buvarruha")
    public void aTablaWithABuvarruha() {
        Tabla = new Tabla();
        Tabla.setHoreteg(0);
        Tabla.setDolog(Buvarruha= new Buvarruha());
    }

    @And("an Eszkimo")
    public void anEszkimo() {
        Eszkimo = new Eszkimo();
        Eszkimo.tabla = Tabla;
        Tabla.elfogad(Eszkimo);
    }

    @When("the Eszkimo felvesz Buvarruha")
    public void theEszkimoFelveszBuvarruha() {
        Eszkimo.felvesz();
    }

    @Then("Eszkimo should have Buvarruha")
    public void eszkimoShouldHaveBuvarruha() {
        assert Eszkimo.getDolgok().get(0).equals(Buvarruha.class);
    }
    private Tabla Tabla;
    private Eszkimo Eszkimo;
    private Buvarruha Buvarruha;
}
