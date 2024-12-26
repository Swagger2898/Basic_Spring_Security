package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;

@RestController
public class HelloController {



    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public HelloController(EmailService emailService, AuthenticationManager authenticationManager) {
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject,@RequestParam String message){
        emailService.sendEmail(to, subject,message);
        return "Email sent successfully";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Authentication authentication){

        String userEmail = ((User) authentication.getPrincipal()).getUsername();

        emailService.sendEmail(userEmail, "Login Successfull","you have logged in successfully");
        return "Login successful and email sent to " + userEmail;

    }

    @GetMapping("/public/hello")
    public String publicHello(){

        return "Hello, World! This is a public endpoint. ";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }
}
