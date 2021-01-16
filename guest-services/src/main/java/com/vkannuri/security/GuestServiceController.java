package com.vkannuri.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** @author Venu Kannuri . */
@RestController
@RequestMapping("/guests")
public class GuestServiceController {

  private static final Logger logger = LoggerFactory.getLogger(GuestServiceController.class);

  private final GuestRepository repository;

  public GuestServiceController(GuestRepository guestRepository) {
    this.repository = guestRepository;
  }

  @GetMapping
  public List<Guest> getAllGuests() {
    return repository.findAll();
  }

  @PostMapping
  public ResponseEntity<Guest> addGuest(@RequestBody GuestDto guestDto) {
    Guest guest = this.repository.save(guestDto.translateModelToGuest());
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(guest.getId())
            .toUri();
    return ResponseEntity.created(location).body(guest);
  }

  @GetMapping("/{id}")
  public Guest getGuest(@PathVariable Long id) throws GuestNotFoundException {
    Optional<Guest> guest = this.repository.findById(id);
    if (guest.isPresent()) {
      return guest.get();
    }
    throw new GuestNotFoundException("Guest not found with id: " + id);
  }

  @PutMapping("/{id}")
  public Guest updateGuest(@PathVariable Long id, @RequestBody GuestDto guestDto)
      throws GuestNotFoundException {
    Optional<Guest> existing = this.repository.findById(id);
    if (!existing.isPresent()) {
      throw new GuestNotFoundException("Guest not found with id: " + id);
    }
    Guest guest = guestDto.translateModelToGuest();
    guest.setId(id);
    return this.repository.save(guest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteGuest(@PathVariable Long id) throws GuestNotFoundException {
    Optional<Guest> existing = this.repository.findById(id);
    if (!existing.isPresent()) {
      throw new GuestNotFoundException("Guest not found with id: " + id);
    }
    this.repository.deleteById(id);
  }
}
