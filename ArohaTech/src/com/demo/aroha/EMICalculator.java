package com.demo.aroha;

public class EMICalculator {

  

    public static void main(String[] args) {
        double principal = 500000; 
        double annualInterestRate = 7.5; 
        int tenureInMonths = 60; 

        float emi = caluculateEmi(principal, annualInterestRate, tenureInMonths);
        System.out.printf("Emi per month : "+ emi);
    }
    
    public static float caluculateEmi(double principal, double annualInterestRate, int tenureInMonths) {
        double monthlyInterestRate = annualInterestRate / (12 * 100); // converting annual rate to monthly and percent to decimal

       
        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureInMonths))
                / (Math.pow(1 + monthlyInterestRate, tenureInMonths) - 1);

        return (float)emi;
    }
}

