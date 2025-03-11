package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
