package com.automation.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement; // Replace MobileElement with WebElement
import io.appium.java_client.android.AndroidDriver;

public class HomePage {
	 AndroidDriver driver; // Replace MobileElement with WebElement

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isHomeDisplayed() {
        WebElement homeScreen = driver.findElement(By.id("com.ecommerce:id/homeScreen"));
        return homeScreen.isDisplayed();
    }
}
