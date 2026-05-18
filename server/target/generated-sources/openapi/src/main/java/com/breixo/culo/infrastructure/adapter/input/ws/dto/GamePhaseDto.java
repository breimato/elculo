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
 * Gets or Sets GamePhase
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T10:10:21.786510900+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public enum GamePhaseDto {
  
  LOBBY("LOBBY"),
  
  DEALING("DEALING"),
  
  CULO_SWAP_VOTE("CULO_SWAP_VOTE"),
  
  PLAYING("PLAYING"),
  
  EXCHANGE("EXCHANGE"),
  
  FINISHED("FINISHED");

  private String value;

  GamePhaseDto(String value) {
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
  public static GamePhaseDto fromValue(String value) {
    for (GamePhaseDto b : GamePhaseDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

