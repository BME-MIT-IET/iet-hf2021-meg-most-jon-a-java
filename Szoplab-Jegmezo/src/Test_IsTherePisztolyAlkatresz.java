import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_IsTherePisztolyAlkatresz {
    @Given("There is a Map and a PisztolyAlkatresz")
    public void thereIsAMapAndAPisztolyAlkatresz() {
        Map = new Tabla();
        PisztolyAlkatresz = new PisztolyAlkatresz();
    }

    @When("I put a PisztolyAlkatresz on the Map")
    public void iPutAPisztolyAlkatreszOnTheMap() {
        Map.setDolog(PisztolyAlkatresz);
    }

    @Then("A PisztolyAlkatresz should be on the Map")
    public void aPisztolyAlkatreszShouldBeOnTheMap() {
        assert Map.getDolog().equals(PisztolyAlkatresz.class);
    }

    private Tabla Map;
    private PisztolyAlkatresz PisztolyAlkatresz;
}
