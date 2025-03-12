package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.Direction;
import util.Gestures;

import java.time.Duration;

public class SignUpScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Email\")")
    private WebElement textEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Password\")")
    private WebElement textPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Confirm password\")")
    private WebElement textConfirmPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"button-SIGN UP\")")
    private WebElement btnSignUp;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/alertTitle\")")
    private WebElement alertSignUpConfirmation;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Login\").instance(0)")
    private WebElement btnLogInScreen;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
    private WebElement btnSignUpOk;

    public SignUpScreen(AndroidDriver driver) {
        super(driver);
    }

    public void SignUp(String email, String password) {
        textEmail.sendKeys(email);
        textPassword.sendKeys(password);
        textConfirmPassword.sendKeys(password);
        Gestures.swipeScreen(Direction.UP, driver);
        Gestures.tap(btnSignUp, driver);
    }

    public boolean isSignUpSuccessful() {
        return isElementShown(alertSignUpConfirmation);
    }

    public LogInScreen goToLogInScreen() {
        try {
            waitForElement(btnSignUpOk, Duration.ofSeconds(5));
            if (btnSignUpOk.isDisplayed()) {
                Gestures.tap(btnSignUpOk, driver);
            }
        } finally {
            Gestures.tap(btnLogInScreen, driver);
        }
        return new LogInScreen(driver);
    }
}