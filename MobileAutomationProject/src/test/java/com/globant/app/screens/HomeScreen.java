package com.globant.app.screens;

import com.globant.app.utils.screens.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeScreen extends BaseScreen {
    private static final String id_WEBDRIVERIO_main_txt ="new UiSelector().text(\"WEBDRIVER\")";

    @AndroidFindBy(uiAutomator = id_WEBDRIVERIO_main_txt)
    private WebElement WebDriverIO_txt;

    public HomeScreen(AndroidDriver driver)
    {
        super(driver);
    }


    public Boolean isUnitLabelPresent()
    {
        return isElementDisplayed(WebDriverIO_txt);
    }
}
