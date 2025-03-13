package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.SwipeScreen;

import java.util.List;

public class SwipeTest extends BaseTest {

    private SwipeScreen swipeScreen;

    @BeforeMethod
    public void setUpTest() {
        swipeScreen = baseScreen.goToSwipeScreen();
    }

    @Test(testName = "Swipe right on the cards and verify that the old card is hidden")
    public void swipeHidesPreviousCard() {
        String previousCardId;
        List<String> visibleCardIdList;
        while (!swipeScreen.isCardLast()) {
            previousCardId = swipeScreen.getSelectedCardId();
            swipeScreen.swipeToNextCard();
            visibleCardIdList = swipeScreen.getVisibleCardsIds();
            Assert.assertFalse(visibleCardIdList.contains(previousCardId)); // Assert previous card is not visible
        }
    }

    @Test(testName = "Swipe right to the last card and verify that the card is the only one visible on the screen")
    public void lastCardIsTeOnlyCard() {
        swipeScreen.swipeToLastCard();
        Assert.assertEquals(swipeScreen.getVisibleCardsIds().size(), 1);
    }

}
