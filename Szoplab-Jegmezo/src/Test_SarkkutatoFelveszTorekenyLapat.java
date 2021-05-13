import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_SarkkutatoFelveszTorekenyLapat {
    @Given("a Tabla with a TorekenyLapat")
    public void aTablaWithATorekenyLapat() {
        Tabla = new Tabla();
        Tabla.setHoreteg(0);
        Tabla.setDolog(TorekenyLapat= new TorekenyLapat());
    }

    @And("an SarkkutatoTorekenyLapat")
    public void anSarkkutatoTorekenyLapat() {
        Sarkkutato = new Sarkkutato();
        Sarkkutato.tabla = Tabla;
        Tabla.elfogad(Sarkkutato);
    }

    @When("the Sarkkutato felvesz TorekenyLapat")
    public void theSarkkutatoFelveszTorekenyLapat() {
        Sarkkutato.felvesz();
    }

    @Then("Sarkkutato should have TorekenyLapat")
    public void sarkkutatoShouldHaveTorekenyLapat() {
        assert Sarkkutato.getDolgok().get(0).equals(TorekenyLapat.class);
    }

    private Tabla Tabla;
    private Sarkkutato Sarkkutato;
    private TorekenyLapat TorekenyLapat;
}
