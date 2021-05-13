import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_EszkimoFelveszKotel {
    @Given("a Tabla with a Kotel")
    public void aTablaWithAKotel() {
        Tabla = new Tabla();
        Tabla.setHoreteg(0);
        Tabla.setDolog(Kotel = new Kotel());
    }

    @And("an Eszkimo Kotel")
    public void anEszkimo() {
        Eszkimo = new Eszkimo();
        Eszkimo.tabla = Tabla;
        Tabla.elfogad(Eszkimo);
    }

    @When("the Eszkimo felvesz Kotel")
    public void theEszkimoFelveszKotel() {
        Eszkimo.felvesz();
    }

    @Then("Eszkimo should have Kotel")
    public void eszkimoShouldHaveKotel() {
        assert Eszkimo.getDolgok().get(0).equals(Kotel.class);
    }
    private Tabla Tabla;
    private Eszkimo Eszkimo;
    private Kotel Kotel;
}
