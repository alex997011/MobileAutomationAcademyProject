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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DragScreen extends BaseScreen {

    private static final String id_drag_label_xpath= "//android.widget.TextView[@text=\"Drag\"]";
    private static final String id_drag_drop_xpath = "//android.widget.TextView[@text=\"Drag and Drop\"]";
    private static final int DEFAULT_TIMEOUT_SECONDS = 60;

    @AndroidFindBy(xpath = id_drag_label_xpath)
    private WebElement dragLabel;

    @AndroidFindBy(xpath = id_drag_drop_xpath)
    private WebElement dragDropLabel;

    public DragScreen (AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isDragLabelPresent() {
        return isElementDisplayed(dragLabel);
    }

    public void clickDragLabel() {
        if (isDragLabelPresent()) {
            dragLabel.click();
        } else {
            throw new RuntimeException("Drag label not found!");
        }
    }

    public boolean isDragScreenLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id_drag_label_xpath)));
        return login.isDisplayed();
    }

}
