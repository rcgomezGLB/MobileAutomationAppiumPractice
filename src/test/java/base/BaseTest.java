package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URI;

public class BaseTest {

    protected static AndroidDriver driver;
    protected BaseScreen baseScreen;

    @BeforeMethod
    public void setUp() {
        driver = createAndroidDriver();
        baseScreen = new BaseScreen(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private AndroidDriver createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setApp("android.wdio.native.app.v1.0.8.apk")
                .setDeviceName("Prueba Appium API 35")
                .setPlatformVersion("15")
                .setAppActivity("com.wdiodemoapp.MainActivity");
        try {
            URI uri = new URI("http://127.0.0.1:4723");
            return new AndroidDriver(uri.toURL(), options);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
