package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class FormsScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Form components\")")
    private WebElement viewTitle;

    public FormsScreen(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isScreenShown() {
        return isElementShown(viewTitle);
    }

}
