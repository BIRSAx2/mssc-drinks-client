package dev.mouhieddine.msscdrinksclient.web.client;

import dev.mouhieddine.msscdrinksclient.web.model.DrinkDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * @author : Mouhieddine.dev
 * @since : 2/7/2021, Sunday
 **/
@ConfigurationProperties(prefix = "dev.mouhieddine", ignoreInvalidFields = false)
@Component
public class DrinksClient {
  public final String DRINK_PATH_V1 = "/api/v1/drink/";
  private final RestTemplate restTemplate;
  private String apihost;

  public DrinksClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public DrinkDto getDrinkById(UUID id) {
    return restTemplate.getForObject(apihost + DRINK_PATH_V1 + id.toString(), DrinkDto.class);
  }

  public URI createDrink(DrinkDto drinkDto) {
    return restTemplate.postForLocation(apihost + DRINK_PATH_V1, drinkDto);
  }

  public void deleteDrink(UUID id) {
    restTemplate.delete(apihost + DRINK_PATH_V1 + id.toString());
  }

  public void setApihost(String apihost) {
    this.apihost = apihost;
  }

  public void updateDrink(UUID id, DrinkDto drinkDto) {
    restTemplate.put(apihost + DRINK_PATH_V1 + id.toString(), drinkDto);
  }
}
