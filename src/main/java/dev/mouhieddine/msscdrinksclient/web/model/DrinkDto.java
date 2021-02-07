package dev.mouhieddine.msscdrinksclient.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author : Mouhieddine.dev
 * @since : 2/7/2021, Sunday
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrinkDto {

  private UUID id;
  private String name;
  private String type;
  private Long upc;
}