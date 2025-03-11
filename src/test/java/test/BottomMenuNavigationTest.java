package test;

import base.BaseScreen;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.WebviewScreen;

public class BottomMenuNavigationTest extends BaseTest {

    private BaseScreen baseScreen;

    @BeforeMethod
    public void setUpTest() {
        baseScreen = new BaseScreen(driver);
    }

    @Test(testName = "Navigates to Web view")
    public void navigateToWebview() {
        WebviewScreen webviewScreen = baseScreen.goToWebViewScreen();
        Assert.assertTrue(webviewScreen.isScreenShown());
    }
}
