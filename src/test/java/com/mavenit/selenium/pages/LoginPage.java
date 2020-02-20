package com.mavenit.selenium.pages;

import com.mavenit.selenium.driver.DriverFactory;
import org.openqa.selenium.By;

public class LoginPage extends DriverFactory {


    public String accountLoginPage() {

        driver.findElement(By.cssSelector("a[data-test=\"account\"]")).click();
        String url = driver.getCurrentUrl();
        return url;
    }

    public String accountLoginPageMessage(){
        String message = driver.findElement(By.cssSelector(".h2.panel-header__headline span")).getText();
        return message;
    }
    public String accountLoggedInMessage(){
        String message = driver.findElement(By.cssSelector(".Title__container__2arEZ >p")).getText();
        return message;
    }
}
