package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import util.Gestures;

public class LogInScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"input-email\")")
    private WebElement textEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"input-password\")")
    private WebElement textPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"button-LOGIN\")")
    private WebElement btnLogin;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sign up\")")
    private WebElement btnSignUpScreen;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/alertTitle\")")
    private WebElement alertLoginConfirmation;

    public LogInScreen(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isScreenShown() {
        return isElementShown(btnLogin);
    }

    public SignUpScreen goToSignUpScreen() {
        navigateToBtn(btnSignUpScreen);
        return new SignUpScreen(driver);
    }

    public void logIn(String email, String password) {
        textEmail.sendKeys(email);
        textPassword.sendKeys(password);
        Gestures.tap(btnLogin, driver);
    }

    public boolean isLogInSuccessful() {
        return isElementShown(alertLoginConfirmation);
    }
}
