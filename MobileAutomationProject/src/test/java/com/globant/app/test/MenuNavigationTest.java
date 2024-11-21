package com.globant.app.test;

import com.globant.app.screens.*;
import com.globant.app.utils.test.BaseTest;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class MenuNavigationTest extends BaseTest {
    @Test
    public void  completeMenuNavigationFlow()
    {

        HomeScreen homeScreen = homeScreen();
        Assert.assertTrue(homeScreen.isUnitLabelPresent());


        webViewScreen().clickWebViewLabel();
        WebViewScreen webViewScreen = webViewScreen();
        Assert.assertTrue(webViewScreen.isWebViewScreenLoaded(), "Failed to redirect correctly to WebView Screen");

        loginScreen().clickLoginLabel();
        LoginScreen loginScreen = loginScreen();
        Assert.assertTrue(loginScreen.isLoginFormScreenLoaded(), "Failed to redirect correctly to Login Screen");

        formsScreen().clickFormsLabel();
        FormsScreen formsScreen = formsScreen();
        Assert.assertTrue(formsScreen.isFormScreenLoaded(),"Failed to redirect correctly to Forms Screen");

        swipeScreen().clickSwipeLabel();
        SwipeScreen swipeScreen =swipeScreen();
        Assert.assertTrue(swipeScreen.isSwipeScreenLoaded(),"Failed to redirect correctly to Swipe Screen");

        dragScreen().clickDragLabel();
        DragScreen dragScreen = dragScreen();
        Assert.assertTrue(dragScreen.isDragScreenLoaded(),"Failed to redirect correctly to Drag Screen");
    }
}
