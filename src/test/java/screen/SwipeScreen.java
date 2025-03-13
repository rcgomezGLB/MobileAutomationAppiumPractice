package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Direction;
import util.Gestures;

import java.util.ArrayList;
import java.util.List;

public class SwipeScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Swipe horizontal\")")
    private WebElement viewTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"card\")")
    private List<RemoteWebElement> shownCards; // RemoteWebElement is used because it allows additional methods, such as getId

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"You found me!!!\")")
    private WebElement lblYouFoundMe;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Swipe-screen\")")
    private WebElement scrollView;


    public SwipeScreen(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isScreenShown() {
        return isElementShown(viewTitle);
    }

    public String getSelectedCardId() {
        return getSelectedCard().getId();
    }

    public void swipeToNextCard() {
        String previousCardId = getSelectedCardId();
        Gestures.swipeScreenFromCenter(Direction.LEFT, driver);

        // Wait for card is last or current card id changes (swipe completed)
        Wait<WebDriver> wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_DURATION);
        wait.until(d -> (
                isCardLast() || (!previousCardId.equals(getSelectedCardId()))
                ));
    }

    public void swipeToLastCard() {
        while (!isCardLast()) {
            swipeToNextCard();
        }
    }

    public String getYouFoundMeText() {
        scrollToYouFoundMeElement();
        return lblYouFoundMe.getText();
    }

    public void scrollToYouFoundMeElement() {
        int border = 10;
        Rectangle viewRectangle = scrollView.getRect();
        Point startPoint = new Point(viewRectangle.getX() + viewRectangle.width / 2, viewRectangle.getY() + viewRectangle.height - border);

        Gestures.swipeScreenFromElement(Direction.UP, viewTitle, driver);
        Gestures.swipeScreenFromPoint(Direction.UP, startPoint, driver);

        boolean foundElement = false;
        while (!foundElement) {
            try {
                lblYouFoundMe.isDisplayed();
                foundElement = true;
            } catch (NoSuchElementException e) {
                Gestures.swipeScreenFromCenter(Direction.UP, driver);
            }
        }
    }

    public boolean isCardLast() {
        return shownCards.size() == 1;
    }

    public List<String> getVisibleCardsIds() {
        List<String> idList = new ArrayList<>();
        for (RemoteWebElement card: shownCards) {
            idList.add(card.getId());
        }
        return idList;
    }

    // This method is used because new UiSelector().description("card").instance(0) does not always return the left card!
    public RemoteWebElement getSelectedCard() {
        if (shownCards.isEmpty()) {
            throw new RuntimeException(); // Cards cannot be empty for calling the method
        } else if (shownCards.size() == 1) {
            return shownCards.getFirst(); // Return the only card if it is alone
        }
        RemoteWebElement selectedCard = shownCards.getFirst(); // Initialize with the first card

        for (RemoteWebElement card : shownCards) {
            // Get the location of the card
            int xCoordinate = card.getLocation().getX();

            // Check if this card has a lesser x-coordinate
            if (xCoordinate < selectedCard.getLocation().getX()) {
                selectedCard = card; // Update selectedCard
            }
        }
        return selectedCard;
    }

}
