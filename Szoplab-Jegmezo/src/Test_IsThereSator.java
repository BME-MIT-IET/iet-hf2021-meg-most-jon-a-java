import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_IsThereSator {

    @Given("There is a Map and a Sator")
    public void thereIsAMapAndASator() {
        Map = new Tabla();
        Sator = new Sator();
    }

    @When("I put a Sator on the Map")
    public void iPutASatorOnTheMap() {
        Map.setEpitmeny(Sator);
    }



    @Then("A Sator should be on the Map")
    public void aSatorShouldBeOnTheMap() {assert Map.getEpitmeny().equals(Sator.class);
    }

    private Tabla Map;
    private Sator Sator;
}
