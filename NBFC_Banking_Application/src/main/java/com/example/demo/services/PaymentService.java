package com.example.demo.services;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.entity.Loan;
import com.example.demo.entity.Status;
import com.example.demo.repository.LoanProcessRepository;
import com.example.demo.repository.PaymentRepository;
import com.razorpay.FundAccount;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private LoanProcessRepository loanProcessRepository;
	@Autowired
	private RazorpayClient razorpayClient;

}
