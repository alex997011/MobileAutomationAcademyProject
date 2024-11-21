package com.globant.app.utils.test;

import com.globant.app.screens.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {
    public static String PROPERTIES_FILE ="src/test/resources/config.properties";

    private static Properties properties = new Properties();

    public static AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void enviromentSetup(){

        loadProperties();
        UiAutomator2Options capabilities = new UiAutomator2Options();
        setUpCapabilities(capabilities);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void loadProperties(){
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties files" + PROPERTIES_FILE);
        }

    }


    public static String getCapabilities(String variable)
    {
        return properties.getProperty(variable);
    }

    public static void setUpCapabilities(UiAutomator2Options capabilities)
    {
        capabilities.setPlatformName(getCapabilities("platformName"));
        capabilities.setDeviceName(getCapabilities("deviceName"));
        capabilities.setAppActivity(getCapabilities("appActivity"));
        capabilities.setAppPackage(getCapabilities("appPackage"));
    }

    public HomeScreen homeScreen()
    {
        return new HomeScreen(driver);
    }

    public WebViewScreen webViewScreen()
    {
        return new WebViewScreen(driver);
    }

    public LoginScreen loginScreen()
    {
        return new LoginScreen(driver);
    }

    public FormsScreen formsScreen()
    {
        return new FormsScreen(driver);
    }

    public SwipeScreen swipeScreen()
    {
        return new SwipeScreen(driver);
    }

    public  DragScreen dragScreen()
    {
        return new DragScreen(driver);
    }


    @AfterMethod
    public void downServer() {
        if (driver != null) {
            driver.quit();
        }
    }
}
