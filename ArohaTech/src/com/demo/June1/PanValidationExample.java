package com.demo.June1;


import java.lang.annotation.*;
import java.lang.reflect.*;

// Step 1: Define the annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface ValidPAN {
    String message() default "the PAN format is not right";
}

// Step 2: Class with annotated PAN field
class Customer {
    @ValidPAN(message = "PAN should be in format: ABCDE1234F")
	//@ValidPAN
    //public String pan;
     String pan;

    public Customer(String pan) {
        this.pan = pan;
    }
}

// Step 3: Validator class with main method
public class PanValidationExample {

    //public static void validatePAN(Object obj) throws IllegalAccessException {
    public static void validatePAN(Object obj)  {
    	try {
        Class<?> cls = obj.getClass();
        System.out.println("1. watch class is " + cls);
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(ValidPAN.class)) {
                //field.setAccessible(true);
                System.out.println("2. watch field is " + field);
                String value = (String) field.get(obj);
                System.out.println("3. watch field value is " + value);
                // PAN Format: 5 uppercase letters, 4 digits, 1 uppercase letter
                boolean isValid = value != null && value.matches("[A-Z]{5}[0-9]{4}[A-Z]");

                ValidPAN annotation = field.getAnnotation(ValidPAN.class);
                if (isValid) {
                    System.out.println("NOTE:: Valid PAN: " + value);
                } else {
                    System.out.println("WATCH  " + annotation.message() + " Got: " + value);
                }
            }
        }
    }
    	catch(IllegalAccessException e) {
    		System.out.println("Nature of exception is " + e.getMessage());
    		System.out.println("brother seems to be that the attribute is NOT public ");
    	}
    }

    public static void main(String[] args) throws IllegalAccessException {
        Customer c1 = new Customer("ACCPV5576W");  // valid
        Customer c2 = new Customer("abcde1234f");  // invalid

        System.out.println("Customer 1:");
        validatePAN(c1);

        System.out.println("\nCustomer 2:");
        validatePAN(c2);
        
        Customer c3 = new Customer(null);  // invalid
        validatePAN("*###*$*##(#(#!!");
        
        //Customer c4 = null;
        //validatePAN(c4);
        
    }
}

