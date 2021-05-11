import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Test_ResearcherUsedSkill {
    private  Tabla currentTable;
    private  Tabla neighbourTable;
    private  Sarkkutato researcher;
    private  int logFileLength;
    private  int logFilelengthAfterSkillUsage;
    private  int energyBeforeSkillUsage;
    private  int energyAfterSkillUsage;


    @Given("a table")
    public void aTable() {
        currentTable = new Tabla();
        neighbourTable = new Tabla();

        ArrayList<Tabla>  neighbours = new ArrayList<>();
        neighbours.add(neighbourTable);
        currentTable.setSzomszedok(neighbours);
    }

    @And("a researcher")
    public void aResearcher() {
        researcher = new Sarkkutato();
        researcher.setMunka(2);
        researcher.setTabla(currentTable);
        currentTable.elfogad(researcher);
    }

    @When("the researcher uses it's skill")
    public void theResearcherUsesItSSkill() {
        try {
            logFileLength = countLines("output.txt");
            energyBeforeSkillUsage = researcher.getMunka();

            researcher.kepesseg(0);

            logFilelengthAfterSkillUsage = countLines("output.txt");
            energyAfterSkillUsage = researcher.getMunka();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("it's energy should decrease")
    public void itSEnergyShouldDecrease() {
        assertNotEquals(energyBeforeSkillUsage, energyAfterSkillUsage);
        assertEquals(1, energyAfterSkillUsage);
    }

    @And("and the process should logged into the log file")
    public void andTheProcessShouldLoggedIntoTheLogFile() {
        assertNotEquals(logFileLength, logFilelengthAfterSkillUsage);
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
