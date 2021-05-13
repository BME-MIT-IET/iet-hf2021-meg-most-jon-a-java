import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Test_BearEatsResearcher {
    private  Tabla tableWithPlayer;
    private  Tabla tableWithBear;
    private  Sarkkutato researcher;
    private  Jegesmedve polarBear;


    @Given("a   table")
    public void aTable() {
        tableWithPlayer = new Tabla();
        tableWithBear = new Tabla();

        ArrayList<Tabla> neighbours = new ArrayList<>();
        neighbours.add(tableWithBear);
        tableWithPlayer.setSzomszedok(neighbours);

        neighbours.clear();
        neighbours.add(tableWithPlayer);
        tableWithBear.setSzomszedok(neighbours);
    }

    @And("a  researcher")
    public void aResearcher() {
        researcher = new Sarkkutato();
        researcher.setTabla(tableWithPlayer);
        tableWithPlayer.elfogad(researcher);
    }

    @And("polar bear")
    public void polarBear() {
        polarBear = new Jegesmedve();
        polarBear.setTabla(tableWithPlayer);
    }

    @When("the polar bear moves to the other table to eat the researcher")
    public void thePolarBearMovesToTheOtherTableToEatTheResearcher() {
        polarBear.cselekszik(0);
    }

    @Then("the researchers hp should be {int}")
    public void theResearchersHpShouldBe(int arg0) {
        assertEquals(arg0, researcher.getHp());
    }
}
