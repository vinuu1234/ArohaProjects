package com.demo.aroha.day16;

public class BankRate {
	private double principal;
	private double time;
	private double rateOfInterest;
	private double simpleInterest;
	private double amount;
	public BankRate() {
		super();
	}
	
	public BankRate(double principal, double time) {
		super();
		this.principal = principal;
		this.time = time;
		this.rateOfInterest = 6.5;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	
	public void calcSimpleInterest() {
		simpleInterest=getPrincipal()*getRateOfInterest()*getTime();
	}
	
	public void displayInterest() {
		System.out.println("Simle interest is :"+simpleInterest);
	}
	public void computeAmount() {
		amount=principal+simpleInterest;
	}
	
	

}
