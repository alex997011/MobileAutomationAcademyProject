package com.globant.app.screens;

import com.globant.app.utils.screens.BaseScreen;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.appium.java_client.touch.offset.ElementOption;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static java.time.Duration.ofMillis;

public class SwipeScreen extends BaseScreen {
    private static final Logger log = LoggerFactory.getLogger(DragScreen.class);


    private static final String id_swipe_label_xpath = "//android.widget.TextView[@text=\"Swipe\"]";
    private static final String id_swipe_horizontal_xpath = "//android.widget.TextView[@text=\"Swipe horizontal\"]";
    private static final int DEFAULT_TIMEOUT_SECONDS = 90;

    private static final String id_card_fully_open_source_text = "text(\"FULLY OPEN SOURCE\")";
    private static final String id_card_great_community_text = "text(\"GREAT COMMUNITY\")";
    private static final String id_card_js_foundation_text = "text(\"JS.FOUNDATION\")";
    private static final String id_card_support_videos_text = "text(\"SUPPORT VIDEOS\")";
    private static final String id_card_extendable_text = "text(\"EXTENDABLE\")";
    private static final String id_card_compatible_text = "text(\"COMPATIBLE\")";
    private static final String id_found_me_text = "//android.widget.TextView[@text=\"You found me!!!\"]";


    @AndroidFindBy(xpath = id_swipe_label_xpath)
    private WebElement swipeLabel;

    @AndroidFindBy(xpath = id_swipe_horizontal_xpath)
    private WebElement swipeHorizontalLabel;

    @AndroidFindBy(uiAutomator = id_card_fully_open_source_text)
    private WebElement cardFullyOpenSource;

    @AndroidFindBy(uiAutomator = id_card_great_community_text)
    private WebElement cardGreatCommunity;

    @AndroidFindBy(uiAutomator = id_card_js_foundation_text)
    private WebElement cardJsFoundation;

    @AndroidFindBy(uiAutomator = id_card_support_videos_text)
    private WebElement cardSupportVideos;

    @AndroidFindBy(uiAutomator = id_card_extendable_text)
    private WebElement cardExtendable;

    @AndroidFindBy(uiAutomator = id_card_compatible_text)
    private WebElement cardCompatible;

    @AndroidFindBy(xpath = id_found_me_text)
    private WebElement foundMe;


    public SwipeScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public boolean isSwipeLabelPresent() {
        return isElementDisplayed(swipeLabel);
    }

    public void clickSwipeLabel() {
        if (isSwipeLabelPresent()) {
            swipeLabel.click();
        } else {
            throw new RuntimeException("Swipe label not found!");
        }
    }

    public boolean isSwipeScreenLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id_swipe_horizontal_xpath)));
        log.info("We are in the Swipe Screen");
        return login.isDisplayed();
    }


    public void swipeThroughCards() {
        List<String> cardTexts = Arrays.asList(
                "FULLY OPEN SOURCE",
                "GREAT COMMUNITY",
                "JS.FOUNDATION",
                "SUPPORT VIDEOS",
                "EXTENDABLE"
        );

        for (String cardText : cardTexts) {
            try {

                WebElement card = driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"" + cardText + "\")"
                ));

                swipeCardLeft(card);
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Error when swiping the card: " + cardText);
            }
        }
    }

    public void swipeCardLeft(WebElement cardElement) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(cardElement));

            Point location = cardElement.getLocation();
            Dimension size = cardElement.getSize();


            int startX = location.getX() + (size.getWidth() * 3 / 4);
            int endX = location.getX() + (size.getWidth() / 4);
            int y = location.getY() + (size.getHeight() / 2);

            Actions actions = new Actions(driver);
            actions.moveToElement(cardElement)
                    .clickAndHold()
                    .moveByOffset(-300, 0)
                    .release()
                    .perform();


            Thread.sleep(1000);


            wait.until(ExpectedConditions.invisibilityOf(cardElement));
        } catch (Exception e) {
            System.out.println("Error when swiping on the element: " + cardElement);
            throw e;
        }
    }
    public void swipeVerticalToFindText() {
        try {
            int maxAttempts = 5;

            for (int i = 0; i < maxAttempts; i++) {



                int centerX = 360;
                int startY = 300;
                int endY = -1200;



                Actions actions = new Actions(driver);
                actions
                        .moveToLocation(centerX, startY)
                        .clickAndHold()
                        .moveToLocation(centerX, endY)
                        .release()
                        .perform();


                Thread.sleep(1500);

                try {
                    if (foundMe.isDisplayed()) {

                        return;
                    }
                } catch (Exception e) {

                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}