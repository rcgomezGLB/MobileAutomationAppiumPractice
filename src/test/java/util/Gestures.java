package util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;


public class Gestures {

    public static Point getElementCenter(WebElement element) {
        int x = element.getSize().width/2 + element.getRect().getX();
        int y = element.getSize().height/2 + element.getRect().getY();
        return new Point(x,y);
    }

    private static final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    public static void tap(WebElement element, AndroidDriver driver) {
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), getElementCenter(element)))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger,Duration.ofMillis(100)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }
}
