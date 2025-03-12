package util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
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

    public static void swipeScreen(Direction dir, AndroidDriver driver) {
        int edgeBorder = 10; // better avoid edges
        Point pointStart, pointEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointStart = new Point(dims.width / 2, dims.height / 2);

        pointEnd = switch (dir) {
            case DOWN -> // center of footer
                    new Point(dims.width / 2, dims.height - edgeBorder);
            case UP -> // center of header
                    new Point(dims.width / 2, edgeBorder);
            case LEFT -> // center of left side
                    new Point(edgeBorder, dims.height / 2);
            case RIGHT -> // center of right side
                    new Point(dims.width - edgeBorder, dims.height / 2);
        };

        // Execute Swipe
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),pointStart))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger,Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(100),PointerInput.Origin.viewport(),pointEnd))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
    }
}
