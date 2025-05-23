package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private AppiumDriver driver;

    public CartPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = "test-Cart")
    private WebElement cartIcon;

    @FindBy(id = "test-CHECKOUT")
    private WebElement checkoutButton;

    @FindBy(id = "test-Cart Badge")
    private WebElement cartBadge;

    @FindBy(id = "test-CONTINUE SHOPPING")
    private WebElement continueShopping;

    public void openCart() {
        cartIcon.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public int getCartBadgeCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void tapBackToProducts() {
        continueShopping.click();
    }
}
