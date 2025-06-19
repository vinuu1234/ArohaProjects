package com.demo.aroha.day15;

import java.util.*;

public class JaggedArray {
    public static void main(String[] args) {
        Random rand = new Random();
        int[][] arr = new int[5][]; // 5 rows

        int highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        int highestRow = 0, highestCol = 0;
        int lowestRow = 0, lowestCol = 0;

        System.out.println("Generated Jagged Array:");

        for (int i = 0; i < arr.length; i++) {
            int cols = rand.nextInt(10) + 1; // 1 to 10 columns
            arr[i] = new int[cols];

            for (int j = 0; j < cols; j++) {
                arr[i][j] = rand.nextInt(999) + 1; // 1 to 999

                if (arr[i][j] > highest) {
                    highest = arr[i][j];
                    highestRow = i;
                    highestCol = j;
                }

                if (arr[i][j] < lowest) {
                    lowest = arr[i][j];
                    lowestRow = i;
                    lowestCol = j;
                }

                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        // 1. Highest & Lowest with position
        System.out.println("\nHighest Value: " + highest + " at (" + highestRow + "," + highestCol + ")");
        System.out.println("Lowest Value: " + lowest + " at (" + lowestRow + "," + lowestCol + ")");

        // 2. Row-wise totals
        System.out.println("\nRow Totals:");
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int val : arr[i]) {
                sum += val;
            }
            System.out.println("Row " + i + " Total = " + sum);
        }

        // 3. Column-wise totals (need to find max number of columns)
        int maxCols = 0;
        for (int[] row : arr) {
            maxCols = Math.max(maxCols, row.length);
        }

        System.out.println("\nColumn Totals:");
        for (int col = 0; col < maxCols; col++) {
            int sum = 0;
            for (int row = 0; row < arr.length; row++) {
                if (col < arr[row].length) {
                    sum += arr[row][col];
                }
            }
            System.out.println("Column " + col + " Total = " + sum);
        }

        // 4. Highest & Lowest in second column (index 1)
        int colIndex = 1;
        int highInCol = Integer.MIN_VALUE, lowInCol = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length > colIndex) {
                int val = arr[i][colIndex];
                if (val > highInCol) highInCol = val;
                if (val < lowInCol) lowInCol = val;
            }
        }

        System.out.println("\nSecond Column (Index 1):");
        System.out.println("Highest: " + highInCol);
        System.out.println("Lowest : " + lowInCol);
    }
}
