import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

public class Test_UseSator {

    private Tabla tabla;
    private Eszkimo eszkimo;

    @Given("a Tabla without buildings")
    public void aTablaWithoutBuildings() {
        tabla = new Tabla();
    }

    @And("an Eszkimo with a Sator")
    public void anEszkimoWithASator() {
        eszkimo = new Eszkimo();

        tabla.elfogad(eszkimo);
        eszkimo.setTabla(tabla);

        ArrayList<Dolog> dolgok = new ArrayList<>();
        Sator sator = new Sator();
        dolgok.add(sator);
        eszkimo.setDolgok(dolgok);
    }

    @When("the Eszkimo use Sator")
    public void theEszkimoUseSator() {
        eszkimo.setAktivdolog(0);
        eszkimo.dolgotHasznal(0);
    }

    @Then("the Sator should be felallítva and removed from Eszkimo")
    public void theSatorShouldBeFelallítvaAndRemovedFromEszkimo() {
        assert tabla.getEpitmeny() != null;
    }
}
