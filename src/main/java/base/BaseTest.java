package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.io.File;

public class BaseTest {
    public static AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
    	 DesiredCapabilities caps = new DesiredCapabilities();

         caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 36");
         caps.setCapability(MobileCapabilityType.UDID, "emulator-5554"); // Use `adb devices` to find
         caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
         caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.0"); // Adjust if needed
         //caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
         caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
         //caps.setCapability("chromedriverExecutable", "E:\\Code\\Appium_Learning\\chromedriver-win64\\chromedriver.exe");
         caps.setCapability("app", "E:\\Code\\Appium_Learning\\Demoapp\\DemoApp.apk");
         caps.setCapability("appPackage", "com.swaglabsmobileapp");
         caps.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
         

         // Start Chrome browser or Mobile APK
         driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
