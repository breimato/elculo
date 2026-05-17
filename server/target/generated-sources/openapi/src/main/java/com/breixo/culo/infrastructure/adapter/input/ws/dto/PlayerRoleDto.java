package com.breixo.culo.infrastructure.adapter.input.ws.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets PlayerRole
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T00:12:02.220634600+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public enum PlayerRoleDto {
  
  NONE("NONE"),
  
  GANADOR("GANADOR"),
  
  SUBCAMPEON("SUBCAMPEON"),
  
  PENULTIMO("PENULTIMO"),
  
  CULO("CULO");

  private String value;

  PlayerRoleDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PlayerRoleDto fromValue(String value) {
    for (PlayerRoleDto b : PlayerRoleDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

