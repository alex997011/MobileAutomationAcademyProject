package com.globant.app.screens;

import com.globant.app.utils.screens.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebViewScreen extends BaseScreen {

    private static final String id_WebView_label_xpath = "//android.view.View[@content-desc=\"Webview\"]";
    private static final String xpath_I0_label = "//android.widget.Image[@text=\"WebdriverIO\"]";
    private static final int DEFAULT_TIMEOUT_SECONDS = 60;


    @AndroidFindBy(xpath = id_WebView_label_xpath)
    private WebElement webViewLabel;


    @AndroidFindBy(xpath = xpath_I0_label)
    private WebElement IOlabel;

    public WebViewScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isWebViewLabelPresent() {
        return isElementDisplayed(webViewLabel);
    }

    public void clickWebViewLabel() {
        if (isWebViewLabelPresent()) {
            webViewLabel.click();
        } else {
            throw new RuntimeException("WebView label not found!");
        }
    }

    public boolean isWebViewScreenLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

        WebElement webView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_I0_label)));


        return webView.isDisplayed();

    }
}
