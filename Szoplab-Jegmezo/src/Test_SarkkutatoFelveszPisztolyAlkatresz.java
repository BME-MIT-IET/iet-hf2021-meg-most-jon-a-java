import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.xml.parsers.SAXParser;

public class Test_SarkkutatoFelveszPisztolyAlkatresz {
    @Given("a Tabla with a PisztolyAlkatresz")
    public void aTablaWithAPisztolyAlkatresz() {
        Tabla = new Tabla();
        Tabla.setHoreteg(0);
        Tabla.setDolog(PisztolyAlkatresz= new PisztolyAlkatresz());
    }

    @And("an SarkkutatoPisztolyAlkatresz")
    public void anSarkkutatoPisztolyAlkatresz() {
        Sarkkutato = new Sarkkutato();
        Sarkkutato.tabla = Tabla;
        Tabla.elfogad(Sarkkutato);
    }

    @When("the Sarkkutato felvesz PisztolyAlkatresz")
    public void theSarkkutatoFelveszPisztolyAlkatresz() {
        Sarkkutato.felvesz();
    }

    @Then("Sarkkutato should have PisztolyAlkatresz")
    public void sarkkutatoShouldHavePisztolyAlkatresz() {
        assert Sarkkutato.getDolgok().get(0).equals(PisztolyAlkatresz.class);
    }

    private Tabla Tabla;
    private Sarkkutato Sarkkutato;
    private PisztolyAlkatresz PisztolyAlkatresz;
}
