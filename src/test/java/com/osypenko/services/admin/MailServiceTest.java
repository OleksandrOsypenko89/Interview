package com.osypenko.services.admin;

import com.osypenko.services.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.osypenko.constant.Constant.*;

class MailServiceTest extends BaseTests {
    private final MailService mailService;

    public MailServiceTest(MailService mailService) {
        this.mailService = mailService;
    }


    @Test
    void sendSimpleMessage() {
        mailService.sendSimpleMessage(OLEKSANDR_GMAIL_COM, "This is a test message");
    }

    @Test
    void generatedRandomCode() {
        int code = mailService.generatedRandomCode();
        Assertions.assertTrue(code > START_RANDOM_MAIL_NUM);
        Assertions.assertTrue(code < END_RANDOM_MAIL_NUM);
    }
}