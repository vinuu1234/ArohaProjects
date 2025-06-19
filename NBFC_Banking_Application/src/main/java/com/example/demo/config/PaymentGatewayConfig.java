package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class PaymentGatewayConfig {
    @Value("${payment.gateway.api.key}")
    private String apiKey;

    @Value("${payment.gateway.api.secret}")
    private String apiSecret;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(apiKey, apiSecret);
    }
}