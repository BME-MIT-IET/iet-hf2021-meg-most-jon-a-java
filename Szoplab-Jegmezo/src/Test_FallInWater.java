import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_FallInWater {

    private Viz viz;
    private Eszkimo eszkimo;

    @Given("a Viz")
    public void aViz() {
        viz = new Viz();
    }

    @And("an Eszkimo without Buvarruha")
    public void anEszkimoWithoutBuvarruha() {
        eszkimo = new Eszkimo();
    }

    @When("the Eszkimo fall in the Viz")
    public void theEszkimoFallInTheViz() {
        viz.elfogad(eszkimo);
    }

    @Then("the eszkimo's enery should be zero")
    public void theEszkimoSEneryShouldBeZero() {
        assert eszkimo.getMunka() == 0;
    }
}
