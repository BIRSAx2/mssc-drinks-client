package dev.mouhieddine.msscdrinksclient.web.client;

import dev.mouhieddine.msscdrinksclient.web.model.CustomerDto;
import dev.mouhieddine.msscdrinksclient.web.model.DrinkDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
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
  void createDrink() {
    DrinkDto drinkDto = DrinkDto.builder().name("New Drink").build();
    URI uri = drinksClient.createDrink(drinkDto);
    assertNotNull(uri);
  }

  @Test
  void updateDrink() {
    DrinkDto drinkDto = DrinkDto.builder().name("New Drink").build();
    drinksClient.updateDrink(UUID.randomUUID(), drinkDto);
  }

  @Test
  void deleteDrink() {
    drinksClient.deleteDrink(UUID.randomUUID());
  }

  @Test
  void getDrinkById() {
    DrinkDto drinkDto = drinksClient.getDrinkById(UUID.randomUUID());
    assertNotNull(drinkDto);
  }
  @Test
  void getCustomerById() {
    CustomerDto customerDto = drinksClient.getCustomerById(UUID.randomUUID());
    assertNotNull(customerDto);
  }

  @Test
  void createCustomer() {
    CustomerDto customerDto = CustomerDto.builder().name("New customer").build();
    URI uri = drinksClient.createCustomer(customerDto);
    assertNotNull(uri);
  }

  @Test
  void deleteCustomer() {
    drinksClient.deleteCustomer(UUID.randomUUID());
  }

  @Test
  void updateCustomer() {
    CustomerDto customerDto = CustomerDto.builder().name("New customer").build();
    drinksClient.updateCustomer(UUID.randomUUID(), customerDto);
  }
}