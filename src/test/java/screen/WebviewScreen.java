package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class WebviewScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "text(\"Search (Ctrl+K)\")")
    private WebElement btnSearch;

    public WebviewScreen(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isScreenShown() {
        return isElementShown(btnSearch);
    }

}
