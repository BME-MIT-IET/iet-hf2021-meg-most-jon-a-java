import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Test_EskimoUsedSkill {

    private  Eszkimo eskimo;
    private  Tabla currentTable;
    private  Tabla neighbourTable;
    private  int energyBeforeSkillUsage;
    private  int energyAfterSkillUsage;
    private  int logFileLength;
    private  int logFilelengthAfterSkillUsage;

    @Given("a  table")
    public void aTable() {
        currentTable = new Tabla();
        neighbourTable = new Tabla();

        ArrayList<Tabla> neighbours = new ArrayList<>();
        neighbours.add(neighbourTable);
        currentTable.setSzomszedok(neighbours);
    }

    @And("an eskimo")
    public void anEskimo() {
        eskimo = new Eszkimo();
        eskimo.setMunka(2);
        eskimo.setTabla(currentTable);
        currentTable.elfogad(eskimo);
    }

    @When("the eskimo uses it's skill")
    public void theEskimoUsesItSSkill() {
        try {
            logFileLength = countLines("output.txt");
            energyBeforeSkillUsage = eskimo.getMunka();

            eskimo.kepesseg(0);

            logFilelengthAfterSkillUsage = countLines("output.txt");
            energyAfterSkillUsage = eskimo.getMunka();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("it's  energy should decrease")
    public void itSEnergyShouldDecrease() {
    }

    @And("and  the process should logged into the log file")
    public void andTheProcessShouldLoggedIntoTheLogFile() {
    }

    private  int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
