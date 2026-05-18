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
 * JoinedRoomDto
 */

@JsonTypeName("JoinedRoom")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T12:43:33.152045700+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class JoinedRoomDto {

  private String roomCode;

  private String playerId;

  public JoinedRoomDto roomCode(String roomCode) {
    this.roomCode = roomCode;
    return this;
  }

  /**
   * Get roomCode
   * @return roomCode
   */
  
  @Schema(name = "roomCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("roomCode")
  public String getRoomCode() {
    return roomCode;
  }

  public void setRoomCode(String roomCode) {
    this.roomCode = roomCode;
  }

  public JoinedRoomDto playerId(String playerId) {
    this.playerId = playerId;
    return this;
  }

  /**
   * Get playerId
   * @return playerId
   */
  
  @Schema(name = "playerId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("playerId")
  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JoinedRoomDto joinedRoom = (JoinedRoomDto) o;
    return Objects.equals(this.roomCode, joinedRoom.roomCode) &&
        Objects.equals(this.playerId, joinedRoom.playerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomCode, playerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JoinedRoomDto {\n");
    sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
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

    private JoinedRoomDto instance;

    public Builder() {
      this(new JoinedRoomDto());
    }

    protected Builder(JoinedRoomDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(JoinedRoomDto value) { 
      this.instance.setRoomCode(value.roomCode);
      this.instance.setPlayerId(value.playerId);
      return this;
    }

    public JoinedRoomDto.Builder roomCode(String roomCode) {
      this.instance.roomCode(roomCode);
      return this;
    }
    
    public JoinedRoomDto.Builder playerId(String playerId) {
      this.instance.playerId(playerId);
      return this;
    }
    
    /**
    * returns a built JoinedRoomDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public JoinedRoomDto build() {
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
  public static JoinedRoomDto.Builder builder() {
    return new JoinedRoomDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public JoinedRoomDto.Builder toBuilder() {
    JoinedRoomDto.Builder builder = new JoinedRoomDto.Builder();
    return builder.copyOf(this);
  }

}

