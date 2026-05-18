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
 * CreateRoomRequestDto
 */

@JsonTypeName("CreateRoomRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T10:10:21.786510900+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class CreateRoomRequestDto {

  private String clientId;

  private String nick;

  public CreateRoomRequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateRoomRequestDto(String clientId, String nick) {
    this.clientId = clientId;
    this.nick = nick;
  }

  public CreateRoomRequestDto clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * Get clientId
   * @return clientId
   */
  @NotNull 
  @Schema(name = "clientId", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("clientId")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public CreateRoomRequestDto nick(String nick) {
    this.nick = nick;
    return this;
  }

  /**
   * Get nick
   * @return nick
   */
  @NotNull @Size(min = 1, max = 20) 
  @Schema(name = "nick", example = "Breixo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("nick")
  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateRoomRequestDto createRoomRequest = (CreateRoomRequestDto) o;
    return Objects.equals(this.clientId, createRoomRequest.clientId) &&
        Objects.equals(this.nick, createRoomRequest.nick);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, nick);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateRoomRequestDto {\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    nick: ").append(toIndentedString(nick)).append("\n");
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

    private CreateRoomRequestDto instance;

    public Builder() {
      this(new CreateRoomRequestDto());
    }

    protected Builder(CreateRoomRequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateRoomRequestDto value) { 
      this.instance.setClientId(value.clientId);
      this.instance.setNick(value.nick);
      return this;
    }

    public CreateRoomRequestDto.Builder clientId(String clientId) {
      this.instance.clientId(clientId);
      return this;
    }
    
    public CreateRoomRequestDto.Builder nick(String nick) {
      this.instance.nick(nick);
      return this;
    }
    
    /**
    * returns a built CreateRoomRequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateRoomRequestDto build() {
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
  public static CreateRoomRequestDto.Builder builder() {
    return new CreateRoomRequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateRoomRequestDto.Builder toBuilder() {
    CreateRoomRequestDto.Builder builder = new CreateRoomRequestDto.Builder();
    return builder.copyOf(this);
  }

}

