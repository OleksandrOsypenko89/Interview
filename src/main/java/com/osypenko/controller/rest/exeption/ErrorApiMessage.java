package com.osypenko.controller.rest.exeption;

public class ErrorApiMessage {
    public String errorMessage(String email) {
        return "Пользователь " + email + " уже зарегистрирован!";
    }
}
