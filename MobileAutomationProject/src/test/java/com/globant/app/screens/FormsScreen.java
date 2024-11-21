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

public class FormsScreen extends BaseScreen {

    private static final String id_forms_label_xpath= "//android.widget.TextView[@text=\"Forms\"]";
    private static final String id_forms_components_xpath = "//android.widget.TextView[@text=\"Form components\"]";
    private static final int DEFAULT_TIMEOUT_SECONDS = 60;

    @AndroidFindBy(xpath = id_forms_label_xpath)
    private WebElement formsLabel;

    @AndroidFindBy(xpath = id_forms_components_xpath)
    private WebElement formsComponentLabel;

    public FormsScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isformsLabelPresent() {
        return isElementDisplayed(formsLabel);
    }

    public void clickFormsLabel() {
        if (isformsLabelPresent()) {
            formsLabel.click();
        } else {
            throw new RuntimeException("Forms label not found!");
        }
    }

    public boolean isFormScreenLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id_forms_components_xpath)));

        return login.isDisplayed();
    }
}
