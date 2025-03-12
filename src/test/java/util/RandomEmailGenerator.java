package util;

import java.util.Random;

public class RandomEmailGenerator {

    // Method to generate a random email
    public static String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder email = new StringBuilder();

        // Randomly generate a username with a length between 5 and 10
        int usernameLength = 5 + new Random().nextInt(6); // 5 to 10 characters
        for (int i = 0; i < usernameLength; i++) {
            int index = new Random().nextInt(characters.length());
            email.append(characters.charAt(index));
        }

        // Append a random domain
        email.append("@globant.com");

        return email.toString();
    }
}