package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LogInScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"button-LOGIN\")")
    private WebElement btnLogin;

    public LogInScreen(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isScreenShown() {
        return isElementShown(btnLogin);
    }

}
