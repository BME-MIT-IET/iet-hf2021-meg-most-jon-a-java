import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_IsLapat {
    @Given("There is a Map and a Lapat")
    public void thereIsAMapAndALapat() {
        Map = new Tabla();
        Lapat = new Lapat();
    }

    @When("I put a Lapat on the Map")
    public void iPutALapatOnTheMap() {
        Map.setDolog(Lapat);
    }

    @Then("A Lapat should be on the Map")
    public void aLapatShouldBeOnTheMap() {
        assert Map.getDolog().equals(Lapat.class);
    }
    private Tabla Map;
    private Lapat Lapat;
}
