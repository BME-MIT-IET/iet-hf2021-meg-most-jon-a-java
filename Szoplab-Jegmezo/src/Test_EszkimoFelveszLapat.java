import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_EszkimoFelveszLapat {
    @Given("a Tabla with a Lapat")
    public void aTablaWithALapat() {
        Tabla = new Tabla();
        Tabla.setHoreteg(0);
        Tabla.setDolog(Lapat= new Lapat());
    }
    @And("an Eszkimo Lapat")
    public void anEszkimo() {
        Eszkimo = new Eszkimo();
        Eszkimo.tabla = Tabla;
        Tabla.elfogad(Eszkimo);
    }

    @When("the Eszkimo felvesz Lapat")
    public void theEszkimoFelveszLapat() {
        Eszkimo.felvesz();
    }

    @Then("Eszkimo should have Lapat")
    public void eszkimoShouldHaveLapat() {
        assert Eszkimo.getDolgok().get(0).equals(Lapat.class);
    }
    private Tabla Tabla;
    private Eszkimo Eszkimo;
    private Lapat Lapat;
}
