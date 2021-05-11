import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

public class Test_IsTherePolarBear {
    @Given("There is a Map and a Polar Bear")
    public void thereIsAMapAndAPolarBear() {
        Map = new Tabla();
        PolarBear = new Jegesmedve();
        NPCs = new ArrayList<Npc>();
        NPCs.add(PolarBear);
    }

    @When("I put an Polar Bear on the Map")
    public void iPutAnPolarBearOnTheMap() {
        Map.setNpck(NPCs);
    }

    @Then("An Polar Bear should be on the Map")
    public void anPolarBearShouldBeOnTheMap() {
        assert Map.getNpck().contains(PolarBear);
    }

    private Tabla Map;
    private Jegesmedve PolarBear;
    private ArrayList<Npc> NPCs;
}
