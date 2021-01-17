package com.vkannuri.security;

import java.util.Random;

/** @author Venu Kannuri . */
public class Die {
  private int value;

  public void roll() {
    Random random = new Random();
    value = random.nextInt(6) + 1;
  }

  public int getValue() {
    return value;
  }
}
