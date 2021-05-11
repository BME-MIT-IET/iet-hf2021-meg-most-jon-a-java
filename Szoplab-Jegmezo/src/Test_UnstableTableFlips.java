import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class Test_UnstableTableFlips {
    private  InstabilTabla instabilTabla;
    private  Eszkimo eszkimo;

    @Given("There is a table")
    public void thereIsATable() {
        instabilTabla = new InstabilTabla();
        instabilTabla.setStabilitas(0);
    }

    @And("a player")
    public void aPlayer() {
        eszkimo = new Eszkimo();
        eszkimo.setHp(2);
    }

    @When("There are too many players on the table")
    public void thereAreTooManyPlayersOnTheTable() {
        instabilTabla.elfogad(eszkimo);
    }

    @Then("The players hp should be null")
    public void thePlayersHpShouldBeNull() {
        assertEquals(eszkimo.getHp(),  0);
    }
}
