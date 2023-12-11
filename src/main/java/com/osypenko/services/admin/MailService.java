package com.osypenko.services.admin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.NameLogs.ERROR_OCCURRED_WHILE_SENDING_EMAIL;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class MailService {
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to
            , String text
    ) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setText(text);
            emailSender.send(message);
        } catch (MailException exception) {
            log.error(ERROR_OCCURRED_WHILE_SENDING_EMAIL, exception);
        }
    }

    public int generatedRandomCode() {
        Random random = new Random();
        return random.nextInt(END_RANDOM_MAIL_NUM) + START_RANDOM_MAIL_NUM;
    }
}
