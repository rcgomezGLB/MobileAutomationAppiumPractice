# Mobile Automation Project - README

## Overview

This project is a Java-based mobile automation testing framework built using Appium and TestNG to automate scenarios for the WebdriverIO dummy app on Android devices. The framework is designed to test various functionalities of the app including navigation, sign up, login, and swipe interactions.

## Project Structure

The following describes the file structure of the project:

```
├───src
│   └───test
│       ├───java
│       │   ├───base
│       │   │       BaseScreen.java
│       │   │       BaseTest.java
│       │   │
│       │   ├───screen
│       │   │       DragScreen.java
│       │   │       FormsScreen.java
│       │   │       LogInScreen.java
│       │   │       SignUpScreen.java
│       │   │       SwipeScreen.java
│       │   │       WebviewScreen.java
│       │   │
│       │   ├───test
│       │   │       BottomMenuNavigationTest.java
│       │   │       LogInTest.java
│       │   │       SignUpTest.java
│       │   │       SwipeTest.java
│       │   │
│       │   └───util
│       │           Direction.java
│       │           Gestures.java
│       │           RandomEmailGenerator.java
│       │
│       └───resources
│               suite.xml
```

### Directory Details

- **base**: Contains base classes for screens and tests.
    - `BaseScreen.java`: Provides common functionalities for all screen classes.
    - `BaseTest.java`: Provides common setup and teardown functionalities for all tests.

- **screen**: Contains classes representing different screens of the application.
    - `DragScreen.java`: Handles interactions on the Drag screen.
    - `FormsScreen.java`: Handles interactions on the Forms screen.
    - `LogInScreen.java`: Handles interactions on the Login screen.
    - `SignUpScreen.java`: Handles interactions on the Signup screen.
    - `SwipeScreen.java`: Handles interactions on the Swipe screen.
    - `WebviewScreen.java`: Handles interactions on the Webview screen.

- **test**: Contains test classes for different scenarios.
    - `BottomMenuNavigationTest.java`: Tests navigation on the bottom menu bar.
    - `LogInTest.java`: Tests the successful login functionality.
    - `SignUpTest.java`: Tests the successful signup functionality.
    - `SwipeTest.java`: Tests swipe card interactions.

- **util**: Contains utility classes.
    - `Direction.java`: Provides direction constants for swipe actions.
    - `Gestures.java`: Contains methods for performing gestures like swipe.
    - `RandomEmailGenerator.java`: Generates random email addresses for testing.

- **resources**: Contains configuration files.
    - `suite.xml`: TestNG suite configuration file for running tests.

## Prerequisites

- **Java 21 JDK**: Ensure Java is installed on your machine.
- **Appium**: Install Appium and its dependencies.
- **Android Studio**: Install Android Studio for emulators and SDK tools.
- **TestNG**: Add TestNG to your project dependencies.

## Setup Instructions

1. **Clone the Repository**: Clone this project to your local machine.
   ```bash
   git clone https://github.com/rcgomezGLB/MobileAutomationAppiumPractice
   cd MobileAutomationAppiumPractice
   ```

2. **Install Dependencies**: Use Maven to install dependencies defined in the `pom.xml`.

3. **Set Up Environment**: Ensure Appium server is running and the Android emulator or device is connected.

4. **Run Tests**: Execute the tests using TestNG from your IDE or via command line.
   ```bash
   mvn test
   ```

## Test Scenarios

1. **Bottom Menu Navigation**: Tests navigation through the bottom menu bar and verifies sections.
2. **Successful Sign Up**: Automates the signup process and verifies successful completion.
3. **Successful Login**: Automates the login process with a previously created user.
4. **Swipe Cards**: Automates swiping cards in the Swipe section and verifies visibility and content.

## Notes

- Each test scenario is designed to run independently, ensuring no dependencies on previous test executions.
