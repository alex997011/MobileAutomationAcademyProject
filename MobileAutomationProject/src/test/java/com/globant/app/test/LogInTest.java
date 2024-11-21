package com.globant.app.test;

import com.globant.app.screens.LoginScreen;
import com.globant.app.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTest extends BaseTest {

    @Test
    public void completeLogInFlow(){
        loginScreen().clickLoginLabel();
        LoginScreen loginScreen = loginScreen();
        Assert.assertTrue(loginScreen.isLoginFormScreenLoaded(), "No se redirigió correctamente a la pantalla de Login");

        String password = "Password12345";
        loginScreen.generateUniqueEmail();
        loginScreen.LogIn(password);
        Assert.assertTrue(loginScreen.completeLogIn(),"No message");
    }
}
