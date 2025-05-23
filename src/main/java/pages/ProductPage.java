package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    private AppiumDriver driver;

    public ProductPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = "test-Menu")
    private WebElement menuButton;

    @FindBy(id = "test-About")
    private WebElement aboutButton;

    @FindBy(id = "test-RESET APP STATE")
    private WebElement resetAppButton;

    @FindBy(id = "test-Logout")
    private WebElement logoutButton;

    public void addProductToCart(String productName) {
        WebElement addButton = driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']/..//android.widget.TextView[@text='ADD TO CART']"));
        addButton.click();
    }

    public void removeProductFromCart(String productName) {
        WebElement removeButton = driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']/..//android.widget.TextView[@text='REMOVE']"));
        removeButton.click();
    }

    public boolean isProductInCart(String productName) {
        try {
            driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']/..//android.widget.TextView[@text='REMOVE']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void openProductDetails(String productName) {
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']")).click();
    }

    public boolean isProductDetailDisplayed(String productName) {
        return driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + productName + "')]")).isDisplayed();
    }

    public void openMenu() {
        menuButton.click();
    }

    public void closeMenu() {
        driver.navigate().back(); // or tap outside
    }

    public boolean isMenuOpen() {
        return aboutButton.isDisplayed();
    }

    public void logout() {
        logoutButton.click();
    }

    public void selectAbout() {
        aboutButton.click();
    }

    public boolean isOnAboutPage() {
        return driver.getPageSource().contains("saucelabs.com"); // or some other About page indicator
    }

    public void resetAppState() {
        resetAppButton.click();
    }

    public void sortProducts(String option) {
        WebElement sortDropdown = driver.findElement(By.id("test-Modal Selector Button"));
        sortDropdown.click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + option + "']")).click();
    }

    public boolean isSortedBy(String criteria) {
        // You may need to validate product order based on product names or prices
        return true; // Stubbed - implement your own sorting verification logic
    }

    public boolean isOnProductPage() {
        return driver.getPageSource().contains("PRODUCTS");
    }
}
