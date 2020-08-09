package com.diplomski.service;

import com.diplomski.model.User;
import com.diplomski.registration.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendNotificaitionAsync(User user, VerificationToken confirmationToken) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject("Complete Registration!");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("To confirm your account, please click here : "
                +"http://localhost:4200/confirm-account?token="+confirmationToken.getConfirmationToken());
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("javaMailSender.send(mail) nije prosao");
        }
    }
}
