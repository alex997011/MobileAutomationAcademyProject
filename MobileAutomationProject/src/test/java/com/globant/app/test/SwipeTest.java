package com.globant.app.test;

import com.globant.app.screens.SwipeScreen;
import com.globant.app.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwipeTest extends BaseTest {

    @Test
    public void completeSwipeCardsFlow() throws InterruptedException {
        SwipeScreen swipeScreen = swipeScreen();
        swipeScreen.clickSwipeLabel();
        Assert.assertTrue(swipeScreen.isSwipeScreenLoaded(),
                "Failed to redirect correctly to Swipe Screen");

        swipeScreen.swipeThroughCards();
        swipeScreen.swipeVerticalToFindText();

    }
}
