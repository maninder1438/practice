package com.mavenit.selenium.steps_definations;

import com.mavenit.selenium.pages.HomePage;
import com.mavenit.selenium.pages.ResultsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private HomePage homePage = new HomePage();
    private ResultsPage resultsPage = new ResultsPage();


    @Given("^I am on homepage$")
    public void i_am_on_homepage() {
        String actual = homePage.getCurrentUrl();
        assertThat(actual, endsWith("co.uk/"));
    }

    @When("^I search for product \"([^\"]*)\"$")
    public void i_search_for_product(String arg1) throws InterruptedException {
        homePage.doSearch(arg1);
    }

    @Then("^I should be able to see \"([^\"]*)\" product$")
    public void iShouldBeAbleToSeeProduct(String arg0) {
        List<String> allProductNames = resultsPage.getAllProductNames();
        Assert.assertTrue(resultsPage.getSearchTitle().contains(arg0));
        for (String item : allProductNames) {assertThat(item.toLowerCase(), containsString(arg0));}
    }

    private String selectedRating;
    @And("^Select the product rating \"([^\"]*)\" from the rating filter$")
    public void selectTheProductRatingFromTheRatingFilter(String arg0) throws InterruptedException {
        selectedRating = resultsPage.selectProductRating(arg0);
        Thread.sleep(5000);
    }

    @Then("^I should be able to see the products of selected rating only$")
    public void iShouldBeAbleToSeeTheProductsOfSelectedRatingOnly() {
        List<String> actualRatingList = resultsPage.getAllProductRatings();
        for (String indRating : actualRatingList) {
            assertTrue("Current Products have different Rating ", Double.parseDouble(indRating) >= Double.parseDouble(selectedRating));
        }
    }

}