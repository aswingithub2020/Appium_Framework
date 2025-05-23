package pages;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]")
    private WebElement usernameField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
    private WebElement invalidLoginError;

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'locked out')]")
    private WebElement lockedOutError;

    public void login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
        if (invalidLoginError.isDisplayed()) {
            return invalidLoginError.getText();
        } else if (lockedOutError.isDisplayed()) {
            return lockedOutError.getText();
        } else {
            return "No error message displayed.";
        }
    }

    public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }
}
