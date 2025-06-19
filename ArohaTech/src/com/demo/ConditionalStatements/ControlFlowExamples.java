package com.demo.ConditionalStatements;

public class ControlFlowExamples {
    public static void main(String[] args) {
        // If-else examples
        int number = 10;
        
        if (number > 0) {
            System.out.println("Number is positive");
        } else if (number < 0) {
            System.out.println("Number is negative");
        } else {
            System.out.println("Number is zero");
        }
        
        // Switch example (Java 14+ enhanced switch)
        String day = "Monday";
        switch (day) {
            case "Monday" -> System.out.println("Start of work week");
            case "Friday" -> System.out.println("Almost weekend!");
            case "Saturday", "Sunday" -> System.out.println("Weekend!");
            default -> System.out.println("Midweek day");
        }
        
        // For loop examples
        System.out.println("Counting up:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
        
        System.out.println("Counting down:");
        for (int i = 5; i >= 1; i--) {
            System.out.println(i);
        }
        
        // While loop example
        System.out.println("While loop:");
        int whileCounter = 1;
        while (whileCounter <= 5) {
            System.out.println(whileCounter);
            whileCounter++;
        }
        
        // Do-while loop example
        System.out.println("Do-while loop:");
        int doWhileCounter = 1;
        do {
            System.out.println(doWhileCounter);
            doWhileCounter++;
        } while (doWhileCounter <= 5);
        
        // For-each loop example
        System.out.println("For-each loop:");
        String[] fruits = {"Apple", "Banana", "Cherry"};
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}