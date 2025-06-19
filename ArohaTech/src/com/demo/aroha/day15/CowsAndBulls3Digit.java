package com.demo.aroha.day15;

import java.util.*;

public class CowsAndBulls3Digit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String secret = generateSecretNumber();
        int attempts = 0;

        System.out.println("Welcome to the 3-Digit Cows and Bulls Game!");
        System.out.println("Guess the 3-digit number. No repeating digits.");

        while (true) {
            System.out.print("Enter your guess: ");
            String guess = scanner.nextLine();
            attempts++;

            if (!isValidGuess(guess)) {
                System.out.println(" Enter a 3-digit number with no repeating digits.");
                continue;
            }

            if (guess.equals(secret)) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                break;
            }

            int bulls = countBulls(secret, guess);
            int cows = countCows(secret, guess);

            System.out.println("Bulls: " + bulls + ", Cows: " + cows);
        }

        scanner.close();
    }

    private static String generateSecretNumber() {
        Random rand = new Random();
        List<Integer> digits = new ArrayList<>();
        while (digits.size() < 3) {
            int digit = rand.nextInt(10);
            if (!digits.contains(digit)) {
                digits.add(digit);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }

        return sb.toString();
    }

    private static boolean isValidGuess(String guess) {
        if (guess.length() != 3 || !guess.matches("\\d{3}")) return false;

        Set<Character> unique = new HashSet<>();
        for (char c : guess.toCharArray()) {
            unique.add(c);
        }

        return unique.size() == 3;
    }

    private static int countBulls(String secret, String guess) {
        int bulls = 0;
        for (int i = 0; i < 3; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }
        return bulls;
    }

    private static int countCows(String secret, String guess) {
        int cows = 0;
        for (int i = 0; i < 3; i++) {
            char c = guess.charAt(i);
            if (secret.contains(String.valueOf(c)) && secret.charAt(i) != c) {
                cows++;
            }
        }
        return cows;
    }
}
