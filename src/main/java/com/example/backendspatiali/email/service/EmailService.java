package com.example.backendspatiali.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String emailAddress){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply-raymaps@gmail.com");
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("Activate Your Account");
        simpleMailMessage.setText(
                "Please click here to confirm your account: "+
                        "http://localhost:8080/activate-account?email="+ emailAddress
        );

        javaMailSender.send(simpleMailMessage);
    }
}
