package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.LogInScreen;
import screen.SignUpScreen;
import util.RandomEmailGenerator;

public class SignUpTest extends BaseTest {

    private SignUpScreen signUpScreen;

    @BeforeMethod
    public void setUpTest() {
        LogInScreen logInScreen = baseScreen.goToLogInScreen();
        signUpScreen = logInScreen.goToSignUpScreen();
    }

    @Test
    public void signUpTest() {
        signUpScreen.SignUp(RandomEmailGenerator.generateRandomEmail(),"12345678");
        Assert.assertTrue(signUpScreen.isSignUpSuccessful());
    }

}
