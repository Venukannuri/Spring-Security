package com.vkannuri.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** @author Venu Kannuri . */
@RestController
@RequestMapping("/guests")
public class GuestServiceController {

  private final GuestRepository repository;

  public GuestServiceController(GuestRepository guestRepository) {
    this.repository = guestRepository;
  }

  @GetMapping
  public List<Guest> getAllGuests() {
    return repository.findAll();
  }
}
