import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_IsThereFood {
    @Given("^There is a Map and a Food$")
    public void thereIsAMapAndAFood() {
        Map = new Tabla();
        Food = new Etel();
    }

    @When("I put a Food on the Map")
    public void iPutAFoodOnTheMap() {
        Map.setDolog(Food);
    }

    @Then("^A Food should be on the Map$")
    public void aFoodShouldBeOnTheMap() {
        assert Map.getDolog().equals(Etel.class);
    }

    private Tabla Map;
    private Etel Food;
}
