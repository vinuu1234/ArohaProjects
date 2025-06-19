package com.demo;

//Product interface
interface PaymentMethod {
 void pay(double amount);
}

//Concrete products
class CreditCard implements PaymentMethod {
 @Override
 public void pay(double amount) {
     System.out.println("Paying " + amount + " via Credit Card");
 }
}

class PayPal implements PaymentMethod {
 @Override
 public void pay(double amount) {
     System.out.println("Paying " + amount + " via PayPal");
 }
}

//Creator abstract class
abstract class PaymentProcessor {
 public abstract PaymentMethod createPaymentMethod();
 
 public void processPayment(double amount) {
     PaymentMethod method = createPaymentMethod();
     method.pay(amount);
 }
}

//Concrete creators
class CreditCardProcessor extends PaymentProcessor {
 @Override
 public PaymentMethod createPaymentMethod() {
     return new CreditCard();
 }
}

class PayPalProcessor extends PaymentProcessor {
 @Override
 public PaymentMethod createPaymentMethod() {
     return new PayPal();
 }
}

//Client code
public class FactoryMethodDemo {
 public static void main(String[] args) {
     PaymentProcessor cardProcessor = new CreditCardProcessor();
     cardProcessor.processPayment(100.50); // Pays via Credit Card
     
     PaymentProcessor paypalProcessor = new PayPalProcessor();
     paypalProcessor.processPayment(75.25); // Pays via PayPal
 }
}