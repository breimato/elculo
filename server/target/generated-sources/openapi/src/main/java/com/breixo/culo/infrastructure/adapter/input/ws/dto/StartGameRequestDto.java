package com.breixo.culo.infrastructure.adapter.input.ws.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * StartGameRequestDto
 */

@JsonTypeName("StartGameRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T10:35:20.534190700+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class StartGameRequestDto {

  private String clientId;

  private String roomCode;

  public StartGameRequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public StartGameRequestDto(String clientId, String roomCode) {
    this.clientId = clientId;
    this.roomCode = roomCode;
  }

  public StartGameRequestDto clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * Get clientId
   * @return clientId
   */
  @NotNull 
  @Schema(name = "clientId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("clientId")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public StartGameRequestDto roomCode(String roomCode) {
    this.roomCode = roomCode;
    return this;
  }

  /**
   * Get roomCode
   * @return roomCode
   */
  @NotNull 
  @Schema(name = "roomCode", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("roomCode")
  public String getRoomCode() {
    return roomCode;
  }

  public void setRoomCode(String roomCode) {
    this.roomCode = roomCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartGameRequestDto startGameRequest = (StartGameRequestDto) o;
    return Objects.equals(this.clientId, startGameRequest.clientId) &&
        Objects.equals(this.roomCode, startGameRequest.roomCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, roomCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartGameRequestDto {\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private StartGameRequestDto instance;

    public Builder() {
      this(new StartGameRequestDto());
    }

    protected Builder(StartGameRequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(StartGameRequestDto value) { 
      this.instance.setClientId(value.clientId);
      this.instance.setRoomCode(value.roomCode);
      return this;
    }

    public StartGameRequestDto.Builder clientId(String clientId) {
      this.instance.clientId(clientId);
      return this;
    }
    
    public StartGameRequestDto.Builder roomCode(String roomCode) {
      this.instance.roomCode(roomCode);
      return this;
    }
    
    /**
    * returns a built StartGameRequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public StartGameRequestDto build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static StartGameRequestDto.Builder builder() {
    return new StartGameRequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public StartGameRequestDto.Builder toBuilder() {
    StartGameRequestDto.Builder builder = new StartGameRequestDto.Builder();
    return builder.copyOf(this);
  }

}

