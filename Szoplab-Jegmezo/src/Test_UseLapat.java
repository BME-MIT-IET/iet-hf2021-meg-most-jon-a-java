import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test_UseLapat {

    private Tabla tabla_snow;
    private Eszkimo eszkimo;

    @Given("a Tabla with {int} snow")
    public void anotherTablaWithSnow(int before) {
        tabla_snow = new Tabla();
        tabla_snow.setHoreteg(before);
    }

    @And("an Eszkimo with Lapat")
    public void anEszkimoWithLapat() {
        eszkimo = new Eszkimo();

        ArrayList<Dolog> dolgok = new ArrayList<>();
        Lapat lapat = new Lapat();
        dolgok.add(lapat);
        eszkimo.setDolgok(dolgok);

        eszkimo.setTabla(tabla_snow);
        ArrayList<Karakter> karakterek = new ArrayList<>();
        karakterek.add(eszkimo);
        tabla_snow.setKarakterek(karakterek);
    }

    @When("the Eszkimo use Lapat on snowcovered field")
    public void theEszkimoUseLapatOnSnowcoveredField() {
        eszkimo.setAktivdolog(0);
        eszkimo.dolgotHasznal(0);
    }

    @Then("it should be {int} on the Table")
    public void itShouldBeOnTheTable(int after) {
        assert tabla_snow.getHoreteg() == after;
    }

}
