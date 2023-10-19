package com.osypenko.services.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.osypenko.constant.Constant.END_RANDOM_MAIL_NUM;
import static com.osypenko.constant.Constant.START_RANDOM_MAIL_NUM;

@Slf4j
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
        return random.nextInt(END_RANDOM_MAIL_NUM) + START_RANDOM_MAIL_NUM;
    }
}
