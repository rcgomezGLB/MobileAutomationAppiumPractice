package test;

import base.BaseScreen;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.*;

public class BottomMenuNavigationTest extends BaseTest {

    private BaseScreen baseScreen;

    @BeforeMethod
    public void setUpTest() {
        baseScreen = new BaseScreen(driver);
    }

    @Test(testName = "Navigates to Web view screen")
    public void navigateToWebview() {
        WebviewScreen webviewScreen = baseScreen.goToWebViewScreen();
        Assert.assertTrue(webviewScreen.isScreenShown());
    }

    @Test(testName = "Navigates to Login screen")
    public void navigateToLogin() {
        LogInScreen logInScreen = baseScreen.goToLogInScreen();
        Assert.assertTrue(logInScreen.isScreenShown());
    }

    @Test(testName = "Navigates to Forms screen")
    public void navigateToForms() {
        FormsScreen formsScreen = baseScreen.goToFormsScreen();
        Assert.assertTrue(formsScreen.isScreenShown());
    }

    @Test(testName = "Navigates to Swipe Screen")
    public void navigateToSwipe() {
        SwipeScreen swipeScreen = baseScreen.goToSwipeScreen();
        Assert.assertTrue(swipeScreen.isScreenShown());
    }

    @Test(testName = "Navigates to Drag Screen")
    public void navigateToDrag() {
        DragScreen dragScreen = baseScreen.goToDragScreen();
        Assert.assertTrue(dragScreen.isScreenShown());
    }

    @Test(testName = "Navigates to Home Screen")
    public void navigateToHome() {
        baseScreen.goToDragScreen(); // Navigate first to drag screen
        baseScreen.goToBaseScreen(); // Then go back to home screen
        Assert.assertTrue(baseScreen.isScreenShown());
    }

}
