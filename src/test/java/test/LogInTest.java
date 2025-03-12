package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.LogInScreen;
import screen.SignUpScreen;
import util.RandomEmailGenerator;

public class LogInTest extends BaseTest {

    private LogInScreen logInScreen;
    private final String email = RandomEmailGenerator.generateRandomEmail();

    @BeforeMethod
    public void setUpTest() {
        logInScreen = baseScreen.goToLogInScreen();
        SignUpScreen signUpScreen = logInScreen.goToSignUpScreen();
        signUpScreen.SignUp(email,"12345678");
        signUpScreen.goToLogInScreen();
    }

    @Test
    public void logInTest() {
        String password = "12345678";
        logInScreen.logIn(email, password);
        Assert.assertTrue(logInScreen.isLogInSuccessful());
    }

}
