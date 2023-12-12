package com.osypenko.services.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.NameLogs.ERROR_OCCURRED_WHILE_SENDING_EMAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${spring.mail.password}")
    private String password;

    public int generatedRandomCode() {
        Random random = new Random();
        return random.nextInt(END_RANDOM_MAIL_NUM) + START_RANDOM_MAIL_NUM;
    }

    public void sendSimpleMessage(
            String to
            , String text
    ) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setText(text);
            getJavaMailSender().send(message);
        } catch (MailException exception) {
            log.error(ERROR_OCCURRED_WHILE_SENDING_EMAIL, exception);
        }
    }

    private JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return javaMailSender;
    }
}