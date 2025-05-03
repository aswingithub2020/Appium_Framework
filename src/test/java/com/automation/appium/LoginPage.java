package com.automation.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement; // Replace MobileElement with WebElement
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
	 AndroidDriver driver; // Replace MobileElement with WebElement

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("com.ecommerce:id/username"));
        WebElement passwordField = driver.findElement(By.id("com.ecommerce:id/password"));
        WebElement loginButton = driver.findElement(By.id("com.ecommerce:id/loginButton"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
