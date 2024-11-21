package com.globant.app.screens;

import com.globant.app.utils.screens.BaseScreen;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.xpath.XPath;
import java.time.Duration;

public class LoginScreen extends BaseScreen {

    private static final String id_Login_label = "//android.widget.TextView[@text=\"Login\"]";
    private static final String id_LoginFrom_Label = "//android.widget.TextView[@text=\"Login / Sign up Form\"]";
    private static final int DEFAULT_TIMEOUT_SECONDS = 60;

    private static final String id_sign_up_text = "//android.widget.TextView[@text=\"Sign up\"]";
    private static final String id_confirm_password_text_xpath= "//android.widget.EditText[@content-desc=\"input-repeat-password\"]";
    private static final String id_email_label ="text(\"Email\")";
    private static final String id_password_label="text(\"Password\")";
    private static final String id_sign_up_buttom ="text(\"SIGN UP\")";
    private String uniqueEmail;
    private static final String id_signup_successfully_message = "android:id/message";

    private static final String id_login_button ="text(\"LOGIN\")";
    private static final String id_input_email_text_path= "//android.widget.EditText[@content-desc=\"input-email\"]";

    private static final int SCROLL_TIMEOUT = 10; // segundos para timeout de scroll
    private static final int ANIMATION_TIMEOUT = 2;

    @AndroidFindBy(xpath = id_Login_label)
    private WebElement loginlabel;

    @AndroidFindBy(xpath = id_LoginFrom_Label)
    private WebElement loginFormLabel;

    @AndroidFindBy(xpath = id_sign_up_text)
    private WebElement signuplabel;

    @AndroidFindBy(xpath =id_confirm_password_text_xpath)
    private WebElement confirmPasswordLabel;

    @AndroidFindBy(uiAutomator = id_email_label)
    private WebElement emailSignUp;

    @AndroidFindBy(uiAutomator = id_password_label)
    private WebElement passwordLabel;

    @AndroidFindBy(uiAutomator = id_sign_up_buttom)
    private WebElement signUpButton;

    @AndroidFindBy(id = id_signup_successfully_message)
    private WebElement signUpSuccessfullyMessage;

    @AndroidFindBy(uiAutomator = id_login_button)
    private WebElement logInButton;

    @AndroidFindBy(xpath = id_input_email_text_path)
    private WebElement emailText;


    public LoginScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isLoginLabelPresent() {
        return isElementDisplayed(loginlabel);
    }

    public void clickLoginLabel() {
        if (isLoginLabelPresent()) {
            loginlabel.click();
        } else {
            throw new RuntimeException("Login label not found!");
        }
    }

    public boolean isLoginFormScreenLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id_LoginFrom_Label)));
        return login.isDisplayed();

    }




    public boolean isSignUpTextPresent(){
        return isElementDisplayed(signuplabel);
    }

    public void clickSignUpLabelForm(){
        if(isSignUpTextPresent())
        {
            signuplabel.click();
        }else {
            throw new RuntimeException("Sign Up label is not found in the form");
        }
    }

    public boolean isSignUpScreenLoaded(){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

        WebElement SgnUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id_confirm_password_text_xpath)));

        return SgnUp.isDisplayed();
    }

    public String generateUniqueEmail() {
        uniqueEmail = "user_" + System.currentTimeMillis() + "@gmail.com";
        return  uniqueEmail;
    }


    public void scrollToSignUpButton() {
        try {

            if (isElementInViewport(signUpButton)) {
                return;
            }


            try {
                ((AndroidDriver) driver).findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().resourceId(\"" + id_sign_up_buttom + "\"))" +
                                ".setMaxSearchSwipes(3)"));
            } catch (Exception e) {

                scrollUsingTouchAction();
            }


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SCROLL_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(signUpButton));


            Thread.sleep(ANIMATION_TIMEOUT * 1000);

        } catch (Exception e) {
            throw new RuntimeException("Could not scroll to the Sign Up button " + e.getMessage());
        }
    }

    private boolean isElementInViewport(WebElement element) {
        try {
            Rectangle rect = element.getRect();
            Dimension windowSize = driver.manage().window().getSize();
            return rect.getY() >= 0 && rect.getY() <= windowSize.getHeight();
        } catch (Exception e) {
            return false;
        }
    }

    private void scrollUsingTouchAction() {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        new TouchAction(driver)
                .press(PointOption.point(size.width / 2, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(size.width / 2, endY))
                .release()
                .perform();
    }

    public void SignUp(String password, String confPassword) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));

            wait.until(ExpectedConditions.visibilityOf(emailSignUp));
            wait.until(ExpectedConditions.visibilityOf(passwordLabel));
            wait.until(ExpectedConditions.visibilityOf(confirmPasswordLabel));


            emailSignUp.clear();
            passwordLabel.clear();
            confirmPasswordLabel.clear();


            emailSignUp.sendKeys(uniqueEmail);
            Thread.sleep(300);

            passwordLabel.sendKeys(password);
            Thread.sleep(300);

            confirmPasswordLabel.sendKeys(confPassword);
            Thread.sleep(300);

        } catch (Exception e) {
            throw new RuntimeException("Error filling out the registration form: " + e.getMessage());
        }
    }

    public boolean completeSignUpForm() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));


            scrollToSignUpButton();
            wait.until(ExpectedConditions.elementToBeClickable(signUpButton));


            signUpButton.click();
            Thread.sleep(ANIMATION_TIMEOUT * 1000);


            wait.until(ExpectedConditions.visibilityOf(signUpSuccessfullyMessage));


            boolean isMessageDisplayed = signUpSuccessfullyMessage.isDisplayed();
            boolean hasCorrectText = signUpSuccessfullyMessage.getText().contains("You Successfully signed up!"); // Ajusta según tu texto real

            return isMessageDisplayed && hasCorrectText;

        } catch (Exception e) {
            System.out.println("Error while completing the registration form: " + e.getMessage());
            return false;
        }
    }




    public void LogIn(String password){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id_input_email_text_path)));


        emailSignUp.sendKeys(uniqueEmail);


        passwordLabel.sendKeys(password);



    }

    public boolean completeLogIn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();

        wait.until(ExpectedConditions.visibilityOf(signUpSuccessfullyMessage));
        return signUpSuccessfullyMessage.isDisplayed();
    }

}
