package com.automation.appium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceTests extends BaseTest {

    @Test
    public void test01_loginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("testuser", "password123");
        Assert.assertTrue(homePage.isHomeDisplayed());
    }

}
