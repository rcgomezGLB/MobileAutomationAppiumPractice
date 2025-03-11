package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import screen.WebviewScreen;
import util.Gestures;

import java.time.Duration;

public class BaseScreen {

    protected static Duration DEFAULT_EXPLICIT_WAIT_DURATION = Duration.ofSeconds(30); // Slow PC moment

    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Webview\")")
    WebElement btnWebView;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isScreenShown(){ return true; }

    public WebviewScreen goToWebViewScreen() {
        Gestures.tap(btnWebView, driver);
        return new WebviewScreen(driver);
    }

    public boolean isElementShown(WebElement element) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_DURATION);
            wait.until(d -> element.isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
