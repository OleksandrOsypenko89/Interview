package com.osypenko.exception;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

  public void methodThrowsException() {
    throw new UserException("Войдите в систему");
  }
}
