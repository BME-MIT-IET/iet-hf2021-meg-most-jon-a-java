import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_IsThereRope {
    @Given("There is a Map and a Rope")
    public void thereIsAMapAndARope() {
        Map = new Tabla();
        Rope = new Kotel();
    }

    @When("I put a Rope on the Map")
    public void iPutARopeOnTheMap() {
        Map.setDolog(Rope);
    }

    @Then("A Rope should be on the Map")
    public void aRopeShouldBeOnTheMap() {
        assert Map.getDolog().equals(Kotel.class);
    }

    private Tabla Map;
    private Kotel Rope;
}
