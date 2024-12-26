package com.example.demo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javamailSender;

    public void sendEmail(String to, String subject , String text ){

        MimeMessage message = javamailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("swapnilvrinda@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            javamailSender.send(message);
            System.out.println("Email sent successfully !");
        }
        catch(MessagingException e){
            e.printStackTrace();
            System.out.println("Error sending email: "+e.getMessage());
        }
    }

}
