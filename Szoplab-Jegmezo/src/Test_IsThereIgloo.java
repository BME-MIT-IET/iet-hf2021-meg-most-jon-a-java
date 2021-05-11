import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_IsThereIgloo {
    @Given("There is a Map and a Igloo")
    public void thereIsAMapAndAIgloo() {
        Map = new Tabla();
        Igloo = new Iglu();
    }

    @When("I put an Igloo on the Map")
    public void iPutAnIglooOnTheMap() {
        Map.setEpitmeny(Igloo);
    }

    @Then("An Igloo should be on the Map")
    public void anIglooShouldBeOnTheMap() {
        assert Map.getEpitmeny().equals(Iglu.class);
    }

    private Tabla Map;
    private Iglu Igloo;
}
