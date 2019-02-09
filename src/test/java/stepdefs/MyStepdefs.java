package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepdefs {
    @Given("^I am on the google homepage$")
    public void iAmOnTheGoogleHomepage() {
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String arg0) throws Throwable {

    }

    @Then("^I see results$")
    public void iSeeResults() {
    }
}
