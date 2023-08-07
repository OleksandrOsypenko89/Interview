package com.osypenko.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to
            , String text
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);
        emailSender.send(message);
    }

    public int generatedRandomCode() {
        Random random = new Random();
        return random.nextInt((999999 - 100000) + 1) + 100000;
    }
}
