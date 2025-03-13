package util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;


public class Gestures {

    private static final int EDGE_BORDER = 10; // better avoid edges

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

    public static void swipeScreenFromCenter(Direction dir, AndroidDriver driver) {
        Point pointStart, pointEnd;
        // init screen variables
        Dimension dims = getScreenDimension(driver);
        // init start point = center of screen
        pointStart = new Point(dims.width / 2, dims.height / 2);
        pointEnd = getScreenEdgeCenterPoint(dir, driver);

        swipePoints(driver, pointStart, pointEnd);
    }

    public static void swipeScreenFromElement(Direction dir, WebElement element, AndroidDriver driver) {

        Point pointStart, pointEnd;
        Rectangle rect = element.getRect();
        pointStart = new Point(
                rect.getX() + rect.width / 2,
                rect.getY() + rect.height / 2
        );
        pointEnd = getScreenEdgeCenterPoint(dir, driver);


        swipePoints(driver, pointStart, pointEnd);
    }

    public static void swipeScreenFromPoint(Direction dir, Point pointStart, AndroidDriver driver) {
        Point pointEnd;
        pointEnd = getScreenEdgeCenterPoint(dir, driver);
        swipePoints(driver, pointStart, pointEnd);
    }

    public static void swipePoints(AndroidDriver driver, Point pointStart, Point pointEnd) {

        // Execute Swipe
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),pointStart))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger,Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(100),PointerInput.Origin.viewport(),pointEnd))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));

    }

    public static Dimension getScreenDimension(AndroidDriver driver) {
        return driver.manage().window().getSize();
    }

    public static Point getScreenEdgeCenterPoint(Direction dir, AndroidDriver driver) {
        Dimension dims = getScreenDimension(driver);
        return switch (dir) {
            case DOWN -> // center of footer
                    new Point(dims.width / 2, dims.height - EDGE_BORDER);
            case UP -> // center of header
                    new Point(dims.width / 2, EDGE_BORDER);
            case LEFT -> // center of left side
                    new Point(EDGE_BORDER, dims.height / 2);
            case RIGHT -> // center of right side
                    new Point(dims.width - EDGE_BORDER, dims.height / 2);
        };

    }
}
