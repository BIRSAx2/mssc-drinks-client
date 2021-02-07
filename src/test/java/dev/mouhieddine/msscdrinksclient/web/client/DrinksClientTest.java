package dev.mouhieddine.msscdrinksclient.web.client;

import dev.mouhieddine.msscdrinksclient.web.model.DrinkDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Mouhieddine.dev
 * @since : 2/7/2021, Sunday
 **/

@SpringBootTest
class DrinksClientTest {
  @Autowired
  DrinksClient drinksClient;

  @BeforeEach
  void setUp() {
  }

  @Test
  void getDrinkById() {
    DrinkDto drinkDto = drinksClient.getDrinkById(UUID.randomUUID());
    assertNotNull(drinkDto);
  }
}