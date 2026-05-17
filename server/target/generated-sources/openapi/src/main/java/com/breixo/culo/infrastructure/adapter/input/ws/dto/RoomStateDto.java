package com.breixo.culo.infrastructure.adapter.input.ws.dto;

import java.net.URI;
import java.util.Objects;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.GamePhaseDto;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.PlayerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RoomStateDto
 */

@JsonTypeName("RoomState")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T00:12:02.220634600+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class RoomStateDto {

  private String roomCode;

  private String hostPlayerId;

  private GamePhaseDto phase;

  @Valid
  private List<@Valid PlayerDto> players = new ArrayList<>();

  public RoomStateDto roomCode(String roomCode) {
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

  public RoomStateDto hostPlayerId(String hostPlayerId) {
    this.hostPlayerId = hostPlayerId;
    return this;
  }

  /**
   * Get hostPlayerId
   * @return hostPlayerId
   */
  
  @Schema(name = "hostPlayerId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hostPlayerId")
  public String getHostPlayerId() {
    return hostPlayerId;
  }

  public void setHostPlayerId(String hostPlayerId) {
    this.hostPlayerId = hostPlayerId;
  }

  public RoomStateDto phase(GamePhaseDto phase) {
    this.phase = phase;
    return this;
  }

  /**
   * Get phase
   * @return phase
   */
  @Valid 
  @Schema(name = "phase", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phase")
  public GamePhaseDto getPhase() {
    return phase;
  }

  public void setPhase(GamePhaseDto phase) {
    this.phase = phase;
  }

  public RoomStateDto players(List<@Valid PlayerDto> players) {
    this.players = players;
    return this;
  }

  public RoomStateDto addPlayersItem(PlayerDto playersItem) {
    if (this.players == null) {
      this.players = new ArrayList<>();
    }
    this.players.add(playersItem);
    return this;
  }

  /**
   * Get players
   * @return players
   */
  @Valid 
  @Schema(name = "players", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("players")
  public List<@Valid PlayerDto> getPlayers() {
    return players;
  }

  public void setPlayers(List<@Valid PlayerDto> players) {
    this.players = players;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoomStateDto roomState = (RoomStateDto) o;
    return Objects.equals(this.roomCode, roomState.roomCode) &&
        Objects.equals(this.hostPlayerId, roomState.hostPlayerId) &&
        Objects.equals(this.phase, roomState.phase) &&
        Objects.equals(this.players, roomState.players);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomCode, hostPlayerId, phase, players);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomStateDto {\n");
    sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
    sb.append("    hostPlayerId: ").append(toIndentedString(hostPlayerId)).append("\n");
    sb.append("    phase: ").append(toIndentedString(phase)).append("\n");
    sb.append("    players: ").append(toIndentedString(players)).append("\n");
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

    private RoomStateDto instance;

    public Builder() {
      this(new RoomStateDto());
    }

    protected Builder(RoomStateDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(RoomStateDto value) { 
      this.instance.setRoomCode(value.roomCode);
      this.instance.setHostPlayerId(value.hostPlayerId);
      this.instance.setPhase(value.phase);
      this.instance.setPlayers(value.players);
      return this;
    }

    public RoomStateDto.Builder roomCode(String roomCode) {
      this.instance.roomCode(roomCode);
      return this;
    }
    
    public RoomStateDto.Builder hostPlayerId(String hostPlayerId) {
      this.instance.hostPlayerId(hostPlayerId);
      return this;
    }
    
    public RoomStateDto.Builder phase(GamePhaseDto phase) {
      this.instance.phase(phase);
      return this;
    }
    
    public RoomStateDto.Builder players(List<@Valid PlayerDto> players) {
      this.instance.players(players);
      return this;
    }
    
    /**
    * returns a built RoomStateDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public RoomStateDto build() {
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
  public static RoomStateDto.Builder builder() {
    return new RoomStateDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public RoomStateDto.Builder toBuilder() {
    RoomStateDto.Builder builder = new RoomStateDto.Builder();
    return builder.copyOf(this);
  }

}

