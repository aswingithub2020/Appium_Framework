package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private AppiumDriver driver;

    public CheckoutPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = "test-First Name")
    private WebElement firstNameField;

    @FindBy(id = "test-Last Name")
    private WebElement lastNameField;

    @FindBy(id = "test-Zip/Postal Code")
    private WebElement zipCodeField;

    @FindBy(id = "test-CONTINUE")
    private WebElement continueButton;

    @FindBy(id = "test-FINISH")
    private WebElement finishButton;

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'THANK YOU')]")
    private WebElement thankYouMessage;

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'required')]")
    private WebElement formError;

    public void enterCheckoutInfo(String firstName, String lastName, String zip) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        zipCodeField.clear();
        zipCodeField.sendKeys(zip);
        continueButton.click();
    }

    public void finishCheckout() {
        finishButton.click();
    }

    public boolean isOrderConfirmationDisplayed() {
        return thankYouMessage.isDisplayed();
    }

    public String getErrorMessage() {
        return formError.getText();
    }
}
