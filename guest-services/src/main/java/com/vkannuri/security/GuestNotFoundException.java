package com.vkannuri.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** @author Venu Kannuri . */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GuestNotFoundException extends Throwable {
  public GuestNotFoundException(String s) {
    super(s);
  }
}
