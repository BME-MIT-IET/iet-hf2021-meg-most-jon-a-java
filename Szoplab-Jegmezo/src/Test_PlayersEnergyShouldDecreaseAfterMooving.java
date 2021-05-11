import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Test_PlayersEnergyShouldDecreaseAfterMooving {
    private Tabla currentTable;
    private Tabla otherTable;
    private Eszkimo eskimo;

    @Given("there are two tables")
    public void thereAreTwoTables() {
        currentTable = new Tabla();
        otherTable = new Tabla();

        ArrayList<Tabla> neighbours = new ArrayList<>();
        neighbours.add(otherTable);
        currentTable.setSzomszedok(neighbours);
    }

    @And("a player character")
    public void aPlayerCharacter() {
        eskimo = new Eszkimo();
        eskimo.setMunka(1);
        eskimo.setTabla(currentTable);
        currentTable.elfogad(eskimo);
    }

    @When("the player moves from one table to the other")
    public void thePlayerMovesFromOneTableToTheOther() {
        eskimo.lep(0);
    }

    @Then("the players energy should decrease")
    public void thePlayersEnergyShouldDecrease() {
        assertEquals(0,eskimo.getMunka());
    }
}
