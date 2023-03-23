package com.example.backendspatiali.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String emailAddress, String title, String massage){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply-raymaps@gmail.com");
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(massage);

        javaMailSender.send(simpleMailMessage);
    }
}
