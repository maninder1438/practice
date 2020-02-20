package com.mavenit.selenium.steps_definations;

import com.mavenit.selenium.pages.HomePage;
import com.mavenit.selenium.pages.ProductDescriptionPage;
import com.mavenit.selenium.pages.ResultsPage;
import com.mavenit.selenium.pages.TrolleyPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class BasketSteps {

    private HomePage homePage = new HomePage();
    private ResultsPage resultsPage = new ResultsPage();
    private ProductDescriptionPage productDescriptionPage = new ProductDescriptionPage();
    private TrolleyPage trolleyPage = new TrolleyPage();

    @When("^I search for a product of \"([^\"]*)\"$")
    public void iSearchForAProductOf(String arg0) throws InterruptedException {
        homePage.doSearch(arg0);
    }
    private  String actualProduct;
    @And("^Select the random product from product list$")
    public void selectTheRandomProductFromProductList() {
        actualProduct =resultsPage.selectAnyProduct();
    }
    @And("^add the selected product to basket$")
    public void addTheSelectedProductToBasket() throws InterruptedException {
        productDescriptionPage.addToTrolley();
        productDescriptionPage.goToTrolley();
    }

    @Then("^I should be able to see the same selected product in the basket$")
    public void iShouldBeAbleToSeeTheSameSelectedProductInTheBasket() throws InterruptedException {
        Thread.sleep(5000);
        String expected = trolleyPage.getProductNameInTrolley();
        assertThat(expected, is(equalToIgnoringCase(actualProduct)));
    }

    private String totalPriceOfSelectedProducts;
    @And("^change the quantity to \"([^\"]*)\"$")
    public void changeTheQuantityTo(String arg0) throws InterruptedException {
        double selectedProductPrice = Double.parseDouble(productDescriptionPage.productPrice());
        double qtySelected = Double.parseDouble(productDescriptionPage.quantityToSelect(arg0));
        totalPriceOfSelectedProducts = String.format("%.2f", selectedProductPrice * qtySelected);
    }
    @Then("^I should be able to see the correct price in the basket$")
    public void iShouldBeAbleToSeeTheCorrectPriceInTheBasket() {
        String totalPriceOfBasketProducts = trolleyPage.getProductPriceInTrolley().replace("£", "");
        assertThat(totalPriceOfSelectedProducts, is(equalToIgnoringCase(totalPriceOfBasketProducts)));
    }
    
    private  double selectedProductPrice1;
    @And("^add the selected product to basket and click continue shopping$")
    public void addTheSelectedProductToBasketAndClickContinueShopping() throws InterruptedException {
        selectedProductPrice1 = Double.parseDouble(productDescriptionPage.productPrice());
        productDescriptionPage.addToTrolley();
        productDescriptionPage.continueShopping();
    }
    private  double selectedProductPrice2;
    @And("^add the selected product to basket and click goto trolley$")
    public void addTheSelectedProductToBasketAndClickGotoTrolley() throws InterruptedException {
        selectedProductPrice2 = Double.parseDouble(productDescriptionPage.productPrice());
        productDescriptionPage.addToTrolley();
        productDescriptionPage.goToTrolley();
    }


    @Then("^I should be able to see \"([^\"]*)\" products in total in the basket$")
    public void iShouldBeAbleToSeeProductsInTotalInTheBasket(byte arg0) {
        int numberOfProductsInBasket = trolleyPage.numberOfProductsinTrolley();
        assertEquals(arg0, numberOfProductsInBasket);
    }


    @And("^The price should be equal to the total of products in the basket$")
    public void thePriceShouldBeEqualToTheTotalOfProductsInTheBasket() {
        String subTotalActual = String.format("%.2f", selectedProductPrice1 + selectedProductPrice2);
        String subTotalExpected = trolleyPage.trolleyTotalProductPrice();
        assertThat(subTotalActual, is(equalToIgnoringCase(subTotalExpected)));
    }

}