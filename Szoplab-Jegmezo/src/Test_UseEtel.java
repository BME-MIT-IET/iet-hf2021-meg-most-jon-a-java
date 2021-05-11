import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

public class Test_UseEtel {

    private Tabla tabla;
    private Eszkimo eszkimo;

    @Given("a Tabla")
    public void aTabla() {
        tabla = new Tabla();
    }

    @And("an Eszkimo with {int} hp and an Etel")
    public void anEszkimoWithHpAndAnEtel(int arg0) {
        eszkimo = new Eszkimo();

        eszkimo.setHp(arg0);

        ArrayList<Dolog> dolgok = new ArrayList<>();
        Etel etel = new Etel();
        dolgok.add(etel);
        eszkimo.setDolgok(dolgok);
    }

    @When("the Eszkimo use Etel")
    public void theEszkimoUseEtel() {
        eszkimo.setAktivdolog(0);
        eszkimo.dolgotHasznal(0);
    }

    @Then("it's hp should be {int} on the Table")
    public void itSHpShouldBeOnTheTable(int arg0) {
        assert eszkimo.getHp() == arg0;
    }
}
