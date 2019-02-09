import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        format = {  "pretty",
                "html:target/cucumber-report/",
                "json:target/cucumber-report/cucumber.json",
                "rerun:target/cucumber-report/rerun.txt" },
        glue = {"stepdefs"})

public class SingleTest {

}