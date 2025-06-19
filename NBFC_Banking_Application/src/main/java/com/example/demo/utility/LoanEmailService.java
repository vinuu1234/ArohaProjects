package com.example.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.demo.entity.Customer;
import com.example.demo.entity.LoanApplication;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class LoanEmailService {

	@Autowired
    private final JavaMailSender mailSender;
	@Autowired
    private final SpringTemplateEngine templateEngine;
    
    @Value("${loan.email.from}")
    private String fromEmail;
    
    @Value("${loan.email.template.approved}")
    private String approvedTemplate;
    
    @Value("${loan.email.template.denied}")
    private String deniedTemplate;
    
    @Value("${loan.email.template.pending}")
    private String pendingTemplate;
    
        
    public LoanEmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendStatusUpdateEmail(LoanApplication loan, Customer customer) {
        String template;
        String subject;
        
        switch(loan.getStatus()) {
            case Approved:
                template = approvedTemplate;
                subject = "Congratulations! Your Loan Application #" + loan.getApplicationId() + " is Approved";
                break;
            case Rejected:
                template = deniedTemplate;
                subject = "Update on Your Loan Application #" + loan.getApplicationId();
                break;
            case Pending:
            default:
                template = pendingTemplate;
                subject = "Update on Your Loan Application #" + loan.getApplicationId();
        }
        
        Context context = new Context();
        context.setVariable("loan", loan);
        context.setVariable("customer", customer);
        
        String htmlContent = templateEngine.process(template, context);
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        
        try {
            helper.setFrom(fromEmail);
            helper.setTo(customer.getEmail());
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = isHtml
            
           // mailSender.send("Email sent successufully!!!");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}