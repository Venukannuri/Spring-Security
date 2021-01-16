package com.vkannuri.security.app.service;

import com.vkannuri.security.app.domain.Guest;
import com.vkannuri.security.app.domain.GuestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/** @author Venu Kannuri . */
@Service
public class GuestService {
  private static final String GUESTS = "/guests";
  private static final String ROOT = "/";

  @Value("${infinity.guest.service.url}")
  private String guestServiceUrl;

  private final RestTemplate restTemplate = new RestTemplate();

  public List<Guest> getAllGuest() {
    String url = guestServiceUrl + GUESTS;
    HttpEntity<String> request = new HttpEntity<>(null, null);
    List<Guest> guestList =
        restTemplate
            .exchange(
                url, HttpMethod.GET, request, new ParameterizedTypeReference<List<Guest>>() {})
            .getBody();

    return guestList;
  }

  public Guest addGuest(GuestDto guestDto) {
    String url = guestServiceUrl + GUESTS;
    HttpEntity<GuestDto> request = new HttpEntity<>(guestDto, null);
    return this.restTemplate.exchange(url, HttpMethod.POST, request, Guest.class).getBody();
  }

  public Guest getGuest(long id) {
    String url = guestServiceUrl + GUESTS + ROOT + id;
    HttpEntity<String> request = new HttpEntity<>(null, null);
    return this.restTemplate.exchange(url, HttpMethod.GET, request, Guest.class).getBody();
  }

  public Guest updateGuest(long id, GuestDto guestModel) {
    System.out.println(guestModel);
    String url = guestServiceUrl + GUESTS + ROOT + id;
    HttpEntity<GuestDto> request = new HttpEntity<>(guestModel, null);
    return this.restTemplate.exchange(url, HttpMethod.PUT, request, Guest.class).getBody();
  }
}
