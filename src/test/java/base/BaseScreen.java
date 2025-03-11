package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import screen.*;
import util.Gestures;

import java.time.Duration;

public class BaseScreen {

    protected static Duration DEFAULT_EXPLICIT_WAIT_DURATION = Duration.ofSeconds(30); // Slow PC moment

    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"WEBDRIVER\")")
    WebElement viewHomeTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Home\")")
    WebElement btnHome;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Webview\")")
    WebElement btnWebView;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Login\")")
    WebElement btnLogin;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Forms\")")
    WebElement btnForms;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Swipe\")")
    WebElement btnSwipe;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Drag\")")
    WebElement btnDrag;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isScreenShown() {
        return isElementShown(viewHomeTitle);
    }

    public void navigateToBtn(WebElement element) {
        waitForElement(element);
        Gestures.tap(element, driver);
    }

    public void goToBaseScreen() {
        navigateToBtn(btnHome);
    }
    public WebviewScreen goToWebViewScreen() {
        navigateToBtn(btnWebView);
        return new WebviewScreen(driver);
    }

    public LogInScreen goToLogInScreen() {
        navigateToBtn(btnLogin);
        return new LogInScreen(driver);
    }

    public FormsScreen goToFormsScreen() {
        navigateToBtn(btnForms);
        return new FormsScreen(driver);
    }

    public SwipeScreen goToSwipeScreen() {
        navigateToBtn(btnSwipe);
        return new SwipeScreen(driver);
    }

    public DragScreen goToDragScreen() {
        navigateToBtn(btnDrag);
        return new DragScreen(driver);
    }

    public boolean isElementShown(WebElement element) {
        try {
            waitForElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForElement(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_DURATION);
        wait.until(d -> element.isDisplayed());
    }
}
