 package com.demo.aroha;



public class CompoundInterestCalculator {

    public static double calculateCompoundInterest(double principal, double rate, int time,double timeOfInterestCompounded) {
        double amount = principal * Math.pow((1 + rate / 100), time*timeOfInterestCompounded);
        return amount ;
    }

    public static void main(String[] args) {
        double principal = 10000; // initial amount
        double rate = 5.0;        // annual interest rate in percent
        int time = 3;    
        double timeOfInterestCompounded=24;// time in years

        double compoundInterest = calculateCompoundInterest(principal, rate, time,timeOfInterestCompounded);
        System.out.println(compoundInterest);

    }
}
