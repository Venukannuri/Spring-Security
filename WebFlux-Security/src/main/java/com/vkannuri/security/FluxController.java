package com.vkannuri.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/** @author Venu Kannuri . */
@RestController
@RequestMapping
public class FluxController {

  private DicePairRoll dice = new DicePairRoll();

  @GetMapping("/hey")
  Mono<String> getGreeting() {
    return Mono.just("Hello LinkedIn Learning! Never stop learning");
  }

  @GetMapping("/roll")
  Mono<DicePairRoll> getDiceRoll() {
    dice.rollDice();
    return Mono.just(dice);
  }
}
