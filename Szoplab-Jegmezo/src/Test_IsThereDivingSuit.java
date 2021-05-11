import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_IsThereDivingSuit {
    @Given("There is a Map and a Diving Suit")
    public void thereIsAMapAndADivingSuit() {
        Map = new Tabla();
        DivingSuit = new Buvarruha();
    }

    @When("I put a Diving Suit on the Map")
    public void iPutADivingSuitOnTheMap() {
        Map.setDolog(DivingSuit);
    }

    @Then("A Diving Suit should be on the Map")
    public void aDivingSuitShouldBeOnTheMap() {
        assert Map.getDolog().equals(Buvarruha.class);
    }

    private Tabla Map;
    private Buvarruha DivingSuit;
}
