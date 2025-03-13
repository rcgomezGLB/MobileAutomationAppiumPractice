package screen;

import base.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        Gestures.swipeScreen(Direction.LEFT, driver);

        // Wait for card is last or current card id changes (swipe completed)
        Wait<WebDriver> wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_DURATION);
        wait.until(d -> (
                isCardLast() || (!previousCardId.equals(getSelectedCardId()))
                ));
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
