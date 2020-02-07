package com.mavenit.argos;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class SmokeTestSuite extends Hooks {

    @Test
    public void verifyURL() {
        //Assert - 1
        // product search - entering "puma" to the search bar and search for puma
        driver.findElement(By.cssSelector("input[data-test='search-input']")).sendKeys("puma");
        driver.findElement(By.cssSelector("button[type='submit']")).sendKeys(Keys.ENTER);

        //to verify that we are getting searched text"puma" in the end of url
        String url = driver.getCurrentUrl();
        assertThat("Not got results of search term. ", url, endsWith("puma"));
        System.out.println("Congratulations you can see 'puma' in the end of url: "+url);

    }
@Test
    public void listProductsForStringName() throws InterruptedException {
        //Assert -2
        //Collect a item to list - collect names only of all products of puma
        //loop and verify
        //list of products contain string "puma"
        driver.findElement(By.cssSelector("input[data-test='search-input']")).sendKeys("puma");
        driver.findElement(By.cssSelector("button[type='submit']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        List<WebElement> productWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));

        for (WebElement indProduct : productWebElements) {
            String actual = indProduct.getText();
            assertThat(actual.toLowerCase(), containsString("puma"));

            System.out.println(actual.toLowerCase());
        }
    System.out.println("Congratulations now you can see string name 'puma' in all above products");
    }
@Test
    public void verifySearchTitle() throws InterruptedException {
        //Assert -3
        //to verify that we can see "puma" on the search title
            driver.findElement(By.cssSelector("input[data-test='search-input']")).sendKeys("puma");
            driver.findElement(By.cssSelector("button[type='submit']")).sendKeys(Keys.ENTER);
            Thread.sleep(4000);
            String actualTitle = driver.findElement(By.className("search-title__term")).getText();
            assertThat(actualTitle, is(equalToIgnoringCase("puma")));
            System.out.println("Congratulation you can see search title name as: "+actualTitle + ", which matches to searched string as 'puma'");

    }
@Test
    public void verifyProductRating() throws InterruptedException {
        //    Assert -4
        //verify rating - search for puma and select rating
        //Collect all searched products to list - collect RATING ONLY of all products from list of products
        //loop and verify
        // for example we have selected the products with 5 star rating
        driver.findElement(By.cssSelector("input[data-test='search-input']")).sendKeys("Smart watches");
        driver.findElement(By.cssSelector("button[type='submit']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//strong[contains(text(),'Customer Rating')]")).click();
        driver.findElement(By.cssSelector("label[id=\"5-label\"]")).click();
        String selectedRating = "5.0";
        Thread.sleep(4000);

        List<WebElement> ratingWebElements = driver.findElements(By.cssSelector("div[data-test=\"component-ratings\"]"));
        for (WebElement indProductRating : ratingWebElements) {
            String currentRating = indProductRating.getAttribute("data-star-rating");
            double currentRating1 = Double.parseDouble(currentRating);

            // now to verify that previously selected rating is same for all current products
            assertTrue("Current Products have different Rating ", Double.parseDouble(currentRating) > Double.parseDouble(selectedRating));
            System.out.printf("\nThe Rating of individual product is :" + "%.2f", currentRating1);
        }
    }

    @Test
    public void verifyBasketItem() throws InterruptedException, AWTException {
        //Assert-5
        //search & select macbook air pro
        // make list of all searched products
        // and select random product list of products
        // add to basket and then verify the product is same

        driver.findElement(By.cssSelector("#searchTerm")).sendKeys("tripods, monopods and cases");
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //collecting all products in list
        List<WebElement> allProducts = driver.findElements(By.cssSelector("a[data-test=\"component-product-card-title\"]"));

        //using random class to select random product from list
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        Thread.sleep(4000);
        String productSelected =allProducts.get(randomProduct).getText();
        System.out.println("Total number of products are: " + allProducts.size());
        System.out.println("Index number of random product is : " +randomProduct);

        //using robot class to scroll down page to acceess more web elements
        Robot robot = new Robot();
        if (randomProduct <= 2) {allProducts.get(randomProduct).click();}
        else if (randomProduct>=3) {   robot.keyPress(KeyEvent.VK_PAGE_DOWN); allProducts.get(randomProduct).click(); }

        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button[data-test=\"component-att-button\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("a[data-test=\"component-att-button-basket\"]")).click();
        Thread.sleep(5000);
        String productBasket = driver.findElement(By.xpath("//*[@id=\"basket-content\"]/main/div[2]/section[1]/div[1]/ul/li/div/section/div/div/div[2]/div/div[1]/p/a/span")).getText();

        System.out.println("Actual selected product is: " + productSelected);
        System.out.println("Product in the Basket is:   " + productBasket);
        assertThat(productBasket, is(equalToIgnoringCase(productSelected)));
        System.out.println("Congratulations - Actual selected product & the product in the basket is same");
    }
}






