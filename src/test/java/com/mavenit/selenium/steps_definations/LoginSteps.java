package com.mavenit.selenium.steps_definations;

import com.mavenit.selenium.pages.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();


    @When("^I click on account button$")
    public void i_click_on_account_button(){
        loginPage.accountLoginPage();

    }

    @Then("^I should be able to see account login page$")
    public void i_should_be_able_to_see_account_login_page() {
        String actual = loginPage.accountLoginPage();
        assertThat(actual, endsWith("account"));
    }

    @And("^the message should be visible \"([^\"]*)\"$")
    public void theMessageShouldBeVisible(String arg0){
        String actualMessage = loginPage.accountLoginPageMessage();
        Assert.assertTrue(actualMessage.contains(arg0));
    }
}
