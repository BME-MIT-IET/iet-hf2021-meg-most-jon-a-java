

public class EskimoTest {
    @io.cucumber.java.en.Given("^There is a Map and an Eskimo$")
    public void thereIsAMapAndAnEskimo() {
        Map = new Tabla();
        Eskimo = new Eszkimo();
    }

    @io.cucumber.java.en.When("^I run the test$")
    public void iRunTheTest() {
        Map.elfogad(Eskimo);
    }

    @io.cucumber.java.en.Then("^an Eskimo should be on the Map$")
    public void anEskimoShouldBeOnTheMap() {
        assert Map.getKarakterek().size() > 0;
    }

    private Tabla Map;
    private Eszkimo Eskimo;
}
