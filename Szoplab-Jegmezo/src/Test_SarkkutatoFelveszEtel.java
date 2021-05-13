import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Test_SarkkutatoFelveszEtel {
    @Given("a Tabla with a Etel")
    public void aTablaWithAEtel() {
        Tabla = new Tabla();
        Tabla.setHoreteg(0);
        Tabla.setDolog(Etel= new Etel());
    }

    @And("an SarkkutatoEtel")
    public void anSarkkutatoEtel() {
        Sarkkutato = new Sarkkutato();
        Sarkkutato.tabla = Tabla;
        Tabla.elfogad(Sarkkutato);
    }

    @When("the Sarkkutato felvesz Etel")
    public void theSarkkutatoFelveszEtel() {
        Sarkkutato.felvesz();
    }

    @Then("Sarkkutato should have Etel")
    public void sarkkutatoShouldHaveEtel() {
        assert Sarkkutato.getDolgok().get(0).equals(Etel.class);
    }

    private Tabla Tabla;
    private Sarkkutato Sarkkutato;
    private Etel Etel;
}
