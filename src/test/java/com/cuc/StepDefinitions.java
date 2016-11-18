package com.cuc;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Simon on 16/11/2016.
 */
public class StepDefinitions {

    private TitleLookupService service;
    private String theTitleId;
    @Given("^I use the titlelookup service$")
    public void useTheTitleLookUpService() {
        service = new TitleLookupService();
    }
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Given("^I use incorrect uri for titlelookup service$")
    public void useInvalidUriTitleLookUpService() {
        expectedEx.expect(IOException.class);
        service = new TitleLookupService();
        service.setAlbumsUri("https://jsonplaceholder.typicode.cm/albums?id=");
    }
    @When("^I enter the id (.+)$")
    public void titleId(String aId) {
        theTitleId = aId;
    }
    @Then("^the title (.+) should show up$")
    public void checkTitle(String expectedTitle) {
        try {
            Optional<String> title = service.getTitleById(theTitleId);
            if (title.isPresent())
                Assert.assertEquals(expectedTitle, title.get());
            else
                Assert.assertEquals(expectedTitle, "empty");
        } catch (IOException ioe) {
            Assert.fail();
        }
    }
    @Then("^the user gets an IO Exception$")
    public void IOException() {
        try {
            Optional<String> title = service.getTitleById(theTitleId);

        } catch (IOException ioe) {

        }
    }


}