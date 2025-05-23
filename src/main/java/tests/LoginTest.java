package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {

    private SoftAssert softAssert = new SoftAssert();    
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    @BeforeMethod
    public void setUpPage()
    {
    	 loginPage = new LoginPage(driver);
         productPage = new ProductPage(driver);
         cartPage = new CartPage(driver);
         checkoutPage = new CheckoutPage(driver);    }
    
    @AfterClass
    public void tearDown()
    {
    	softAssert.assertAll();
    }

    @Test(priority = 1)
    public void TC002_invalidLoginTest() {
        loginPage.login("invalid", "wrong_pass");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Username and password do not match any user in this service.");
    }

    @Test(priority = 2)
    public void TC003_lockedOutUserTest() {
        loginPage.login("locked_out_user", "secret_sauce");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Sorry, this user has been locked out.");
    }

    @Test(priority = 3)
    public void TC004_addProductToCart() {
        loginPage.login("standard_user", "secret_sauce");
        productPage.addProductToCart("Sauce Labs Backpack");
        softAssert.assertTrue(productPage.isProductInCart("Sauce Labs Backpack"));
    }

    @Test(priority = 4)
    public void TC005_removeProductFromCart() {
        productPage.removeProductFromCart("Sauce Labs Backpack");
        softAssert.assertFalse(productPage.isProductInCart("Sauce Labs Backpack"));
    }

    @Test(priority = 5)
    public void TC006_verifyCartItemCount() {
        productPage.addProductToCart("Sauce Labs Bike Light");
        productPage.addProductToCart("Sauce Labs Bolt T-Shirt");
        softAssert.assertEquals(cartPage.getCartBadgeCount(), 2);
    }

    @Test(priority = 6)
    public void TC007_viewProductDetails() {
        productPage.openProductDetails("Sauce Labs Backpack");
        softAssert.assertTrue(productPage.isProductDetailDisplayed("Sauce Labs Backpack"));
    }

    @Test(priority = 7)
    public void TC008_completeCheckoutFlow() {
        productPage.addProductToCart("Sauce Labs Backpack");
        cartPage.openCart();
        cartPage.proceedToCheckout();
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");
        checkoutPage.finishCheckout();
        softAssert.assertTrue(checkoutPage.isOrderConfirmationDisplayed());
    }

    @Test(priority = 8)
    public void TC009_checkoutMissingInfo() {
        cartPage.openCart();
        cartPage.proceedToCheckout();
        checkoutPage.enterCheckoutInfo("", "", "");
        softAssert.assertEquals(checkoutPage.getErrorMessage(), "First Name is required");
    }

    @Test(priority = 9)
    public void TC010_backToProductsFromCart() {
        cartPage.openCart();
        cartPage.tapBackToProducts();
        softAssert.assertTrue(productPage.isOnProductPage());
    }

    @Test(priority = 10)
    public void TC011_verifyLogoutFunctionality() {
        productPage.openMenu();
        productPage.logout();
        softAssert.assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test(priority = 11)
    public void TC012_menuOpenCloseTest() {
        productPage.openMenu();
        softAssert.assertTrue(productPage.isMenuOpen());
        productPage.closeMenu();
        softAssert.assertFalse(productPage.isMenuOpen());
    }

    @Test(priority = 12)
    public void TC013_sortingProducts() {
        productPage.sortProducts("Name (Z to A)");
        softAssert.assertTrue(productPage.isSortedBy("Z to A"));
    }

    @Test(priority = 13)
    public void TC014_aboutNavigation() {
        productPage.openMenu();
        productPage.selectAbout();
        softAssert.assertTrue(productPage.isOnAboutPage());
    }

    @Test(priority = 14)
    public void TC015_resetAppStateClearsCart() {
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.openMenu();
        productPage.resetAppState();
        softAssert.assertEquals(cartPage.getCartBadgeCount(), 0);
    }

   
}
