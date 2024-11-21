package com.globant.app.test;

import com.globant.app.screens.LoginScreen;
import com.globant.app.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SignUpTest extends BaseTest {

@Test
    public void completeSignUpFlow()
    {
        loginScreen().clickLoginLabel();
        LoginScreen loginScreen = loginScreen();
        Assert.assertTrue(loginScreen.isLoginFormScreenLoaded(), "Failed to redirect correctly to Login Screen");
        loginScreen().clickSignUpLabelForm();
        Assert.assertTrue(loginScreen.isSignUpScreenLoaded(),"The SignUp form did not load correctly");

        String password = "myPassword123";
        String confirmPassword = "myPassword123";
        loginScreen.generateUniqueEmail();
        loginScreen.SignUp(password,confirmPassword);

        Assert.assertFalse(loginScreen.completeSignUpForm(),"The registration form should not be filled in with invalid data.");
    }

}
