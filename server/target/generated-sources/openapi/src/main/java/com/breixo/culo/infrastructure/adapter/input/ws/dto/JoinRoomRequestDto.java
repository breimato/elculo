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
 * JoinRoomRequestDto
 */

@JsonTypeName("JoinRoomRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T01:54:00.528300900+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class JoinRoomRequestDto {

  private String clientId;

  private String roomCode;

  private String nick;

  public JoinRoomRequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public JoinRoomRequestDto(String clientId, String roomCode, String nick) {
    this.clientId = clientId;
    this.roomCode = roomCode;
    this.nick = nick;
  }

  public JoinRoomRequestDto clientId(String clientId) {
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

  public JoinRoomRequestDto roomCode(String roomCode) {
    this.roomCode = roomCode;
    return this;
  }

  /**
   * Get roomCode
   * @return roomCode
   */
  @NotNull @Size(min = 4, max = 4) 
  @Schema(name = "roomCode", example = "AB3X", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("roomCode")
  public String getRoomCode() {
    return roomCode;
  }

  public void setRoomCode(String roomCode) {
    this.roomCode = roomCode;
  }

  public JoinRoomRequestDto nick(String nick) {
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
    JoinRoomRequestDto joinRoomRequest = (JoinRoomRequestDto) o;
    return Objects.equals(this.clientId, joinRoomRequest.clientId) &&
        Objects.equals(this.roomCode, joinRoomRequest.roomCode) &&
        Objects.equals(this.nick, joinRoomRequest.nick);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, roomCode, nick);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JoinRoomRequestDto {\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
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

    private JoinRoomRequestDto instance;

    public Builder() {
      this(new JoinRoomRequestDto());
    }

    protected Builder(JoinRoomRequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(JoinRoomRequestDto value) { 
      this.instance.setClientId(value.clientId);
      this.instance.setRoomCode(value.roomCode);
      this.instance.setNick(value.nick);
      return this;
    }

    public JoinRoomRequestDto.Builder clientId(String clientId) {
      this.instance.clientId(clientId);
      return this;
    }
    
    public JoinRoomRequestDto.Builder roomCode(String roomCode) {
      this.instance.roomCode(roomCode);
      return this;
    }
    
    public JoinRoomRequestDto.Builder nick(String nick) {
      this.instance.nick(nick);
      return this;
    }
    
    /**
    * returns a built JoinRoomRequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public JoinRoomRequestDto build() {
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
  public static JoinRoomRequestDto.Builder builder() {
    return new JoinRoomRequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public JoinRoomRequestDto.Builder toBuilder() {
    JoinRoomRequestDto.Builder builder = new JoinRoomRequestDto.Builder();
    return builder.copyOf(this);
  }

}

