import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

public class Test_UseTorekenyLapat {
    private Tabla tabla_snow;
    private Eszkimo eszkimo;

    @Given("a Tabla with {int} snowlayer")
    public void aTablaWithSnowlayer(int arg0) {
        tabla_snow = new Tabla();
    }

    @And("an Eszkimo with TorekenyLapat")
    public void anEszkimoWithTorekenyLapat() {
        eszkimo = new Eszkimo();

        ArrayList<Dolog> dolgok = new ArrayList<>();
        TorekenyLapat lapat = new TorekenyLapat();
        dolgok.add(lapat);
        eszkimo.setDolgok(dolgok);

        eszkimo.setTabla(tabla_snow);
        ArrayList<Karakter> karakterek = new ArrayList<>();
        karakterek.add(eszkimo);
        tabla_snow.setKarakterek(karakterek);
    }

    @When("the Eszkimo use TorekenyLapat on snowcovered field")
    public void theEszkimoUseTorekenyLapatOnSnowcoveredField() {
        eszkimo.setAktivdolog(0);
        eszkimo.dolgotHasznal(0);
        eszkimo.dolgotHasznal(0);
    }

    @Then("it should be {int} on the Table and the TorekenyLapat should be removed from inventory")
    public void itShouldBeOnTheTableAndTheTorekenyLapatShouldBeRemovedFromInventory(int arg0) {
        assert tabla_snow.getHoreteg() == arg0 && eszkimo.getDolgok().isEmpty();
    }

}
