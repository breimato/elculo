package com.breixo.culo.infrastructure.adapter.input.ws.dto;

import java.net.URI;
import java.util.Objects;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.CardDto;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.CardRankNameDto;
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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T01:54:00.528300900+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class RoomStateDto {

  private String roomCode;

  private String hostPlayerId;

  private GamePhaseDto phase;

  @Valid
  private List<@Valid PlayerDto> players = new ArrayList<>();

  private String currentPlayerId;

  @Valid
  private List<@Valid CardDto> lastPlayedCards = new ArrayList<>();

  private Integer roundRequirement;

  private CardRankNameDto lastRankName;

  private String culoSwapInitiatorId;

  private String culoSwapTargetId;

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

  public RoomStateDto currentPlayerId(String currentPlayerId) {
    this.currentPlayerId = currentPlayerId;
    return this;
  }

  /**
   * Get currentPlayerId
   * @return currentPlayerId
   */
  
  @Schema(name = "currentPlayerId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currentPlayerId")
  public String getCurrentPlayerId() {
    return currentPlayerId;
  }

  public void setCurrentPlayerId(String currentPlayerId) {
    this.currentPlayerId = currentPlayerId;
  }

  public RoomStateDto lastPlayedCards(List<@Valid CardDto> lastPlayedCards) {
    this.lastPlayedCards = lastPlayedCards;
    return this;
  }

  public RoomStateDto addLastPlayedCardsItem(CardDto lastPlayedCardsItem) {
    if (this.lastPlayedCards == null) {
      this.lastPlayedCards = new ArrayList<>();
    }
    this.lastPlayedCards.add(lastPlayedCardsItem);
    return this;
  }

  /**
   * Get lastPlayedCards
   * @return lastPlayedCards
   */
  @Valid 
  @Schema(name = "lastPlayedCards", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastPlayedCards")
  public List<@Valid CardDto> getLastPlayedCards() {
    return lastPlayedCards;
  }

  public void setLastPlayedCards(List<@Valid CardDto> lastPlayedCards) {
    this.lastPlayedCards = lastPlayedCards;
  }

  public RoomStateDto roundRequirement(Integer roundRequirement) {
    this.roundRequirement = roundRequirement;
    return this;
  }

  /**
   * Get roundRequirement
   * @return roundRequirement
   */
  
  @Schema(name = "roundRequirement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("roundRequirement")
  public Integer getRoundRequirement() {
    return roundRequirement;
  }

  public void setRoundRequirement(Integer roundRequirement) {
    this.roundRequirement = roundRequirement;
  }

  public RoomStateDto lastRankName(CardRankNameDto lastRankName) {
    this.lastRankName = lastRankName;
    return this;
  }

  /**
   * Get lastRankName
   * @return lastRankName
   */
  @Valid 
  @Schema(name = "lastRankName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastRankName")
  public CardRankNameDto getLastRankName() {
    return lastRankName;
  }

  public void setLastRankName(CardRankNameDto lastRankName) {
    this.lastRankName = lastRankName;
  }

  public RoomStateDto culoSwapInitiatorId(String culoSwapInitiatorId) {
    this.culoSwapInitiatorId = culoSwapInitiatorId;
    return this;
  }

  /**
   * Get culoSwapInitiatorId
   * @return culoSwapInitiatorId
   */
  
  @Schema(name = "culoSwapInitiatorId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("culoSwapInitiatorId")
  public String getCuloSwapInitiatorId() {
    return culoSwapInitiatorId;
  }

  public void setCuloSwapInitiatorId(String culoSwapInitiatorId) {
    this.culoSwapInitiatorId = culoSwapInitiatorId;
  }

  public RoomStateDto culoSwapTargetId(String culoSwapTargetId) {
    this.culoSwapTargetId = culoSwapTargetId;
    return this;
  }

  /**
   * Get culoSwapTargetId
   * @return culoSwapTargetId
   */
  
  @Schema(name = "culoSwapTargetId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("culoSwapTargetId")
  public String getCuloSwapTargetId() {
    return culoSwapTargetId;
  }

  public void setCuloSwapTargetId(String culoSwapTargetId) {
    this.culoSwapTargetId = culoSwapTargetId;
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
        Objects.equals(this.players, roomState.players) &&
        Objects.equals(this.currentPlayerId, roomState.currentPlayerId) &&
        Objects.equals(this.lastPlayedCards, roomState.lastPlayedCards) &&
        Objects.equals(this.roundRequirement, roomState.roundRequirement) &&
        Objects.equals(this.lastRankName, roomState.lastRankName) &&
        Objects.equals(this.culoSwapInitiatorId, roomState.culoSwapInitiatorId) &&
        Objects.equals(this.culoSwapTargetId, roomState.culoSwapTargetId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomCode, hostPlayerId, phase, players, currentPlayerId, lastPlayedCards, roundRequirement, lastRankName, culoSwapInitiatorId, culoSwapTargetId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomStateDto {\n");
    sb.append("    roomCode: ").append(toIndentedString(roomCode)).append("\n");
    sb.append("    hostPlayerId: ").append(toIndentedString(hostPlayerId)).append("\n");
    sb.append("    phase: ").append(toIndentedString(phase)).append("\n");
    sb.append("    players: ").append(toIndentedString(players)).append("\n");
    sb.append("    currentPlayerId: ").append(toIndentedString(currentPlayerId)).append("\n");
    sb.append("    lastPlayedCards: ").append(toIndentedString(lastPlayedCards)).append("\n");
    sb.append("    roundRequirement: ").append(toIndentedString(roundRequirement)).append("\n");
    sb.append("    lastRankName: ").append(toIndentedString(lastRankName)).append("\n");
    sb.append("    culoSwapInitiatorId: ").append(toIndentedString(culoSwapInitiatorId)).append("\n");
    sb.append("    culoSwapTargetId: ").append(toIndentedString(culoSwapTargetId)).append("\n");
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
      this.instance.setCurrentPlayerId(value.currentPlayerId);
      this.instance.setLastPlayedCards(value.lastPlayedCards);
      this.instance.setRoundRequirement(value.roundRequirement);
      this.instance.setLastRankName(value.lastRankName);
      this.instance.setCuloSwapInitiatorId(value.culoSwapInitiatorId);
      this.instance.setCuloSwapTargetId(value.culoSwapTargetId);
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
    
    public RoomStateDto.Builder currentPlayerId(String currentPlayerId) {
      this.instance.currentPlayerId(currentPlayerId);
      return this;
    }
    
    public RoomStateDto.Builder lastPlayedCards(List<@Valid CardDto> lastPlayedCards) {
      this.instance.lastPlayedCards(lastPlayedCards);
      return this;
    }
    
    public RoomStateDto.Builder roundRequirement(Integer roundRequirement) {
      this.instance.roundRequirement(roundRequirement);
      return this;
    }
    
    public RoomStateDto.Builder lastRankName(CardRankNameDto lastRankName) {
      this.instance.lastRankName(lastRankName);
      return this;
    }
    
    public RoomStateDto.Builder culoSwapInitiatorId(String culoSwapInitiatorId) {
      this.instance.culoSwapInitiatorId(culoSwapInitiatorId);
      return this;
    }
    
    public RoomStateDto.Builder culoSwapTargetId(String culoSwapTargetId) {
      this.instance.culoSwapTargetId(culoSwapTargetId);
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

