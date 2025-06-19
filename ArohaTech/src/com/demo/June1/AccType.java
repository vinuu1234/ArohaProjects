package com.demo.June1;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AccType {
    String type();
    double minBalance();
    String bankName();
}
