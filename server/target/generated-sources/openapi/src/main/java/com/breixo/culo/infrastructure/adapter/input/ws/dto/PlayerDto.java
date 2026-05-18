package com.breixo.culo.infrastructure.adapter.input.ws.dto;

import java.net.URI;
import java.util.Objects;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.PlayerRoleDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PlayerDto
 */

@JsonTypeName("Player")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T10:10:21.786510900+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class PlayerDto {

  private String id;

  private String nick;

  private Boolean connected;

  private PlayerRoleDto role;

  private Integer cardCount;

  public PlayerDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PlayerDto nick(String nick) {
    this.nick = nick;
    return this;
  }

  /**
   * Get nick
   * @return nick
   */
  
  @Schema(name = "nick", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nick")
  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public PlayerDto connected(Boolean connected) {
    this.connected = connected;
    return this;
  }

  /**
   * Get connected
   * @return connected
   */
  
  @Schema(name = "connected", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("connected")
  public Boolean getConnected() {
    return connected;
  }

  public void setConnected(Boolean connected) {
    this.connected = connected;
  }

  public PlayerDto role(PlayerRoleDto role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
   */
  @Valid 
  @Schema(name = "role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("role")
  public PlayerRoleDto getRole() {
    return role;
  }

  public void setRole(PlayerRoleDto role) {
    this.role = role;
  }

  public PlayerDto cardCount(Integer cardCount) {
    this.cardCount = cardCount;
    return this;
  }

  /**
   * Get cardCount
   * @return cardCount
   */
  
  @Schema(name = "cardCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cardCount")
  public Integer getCardCount() {
    return cardCount;
  }

  public void setCardCount(Integer cardCount) {
    this.cardCount = cardCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerDto player = (PlayerDto) o;
    return Objects.equals(this.id, player.id) &&
        Objects.equals(this.nick, player.nick) &&
        Objects.equals(this.connected, player.connected) &&
        Objects.equals(this.role, player.role) &&
        Objects.equals(this.cardCount, player.cardCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nick, connected, role, cardCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlayerDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nick: ").append(toIndentedString(nick)).append("\n");
    sb.append("    connected: ").append(toIndentedString(connected)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    cardCount: ").append(toIndentedString(cardCount)).append("\n");
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

    private PlayerDto instance;

    public Builder() {
      this(new PlayerDto());
    }

    protected Builder(PlayerDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PlayerDto value) { 
      this.instance.setId(value.id);
      this.instance.setNick(value.nick);
      this.instance.setConnected(value.connected);
      this.instance.setRole(value.role);
      this.instance.setCardCount(value.cardCount);
      return this;
    }

    public PlayerDto.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public PlayerDto.Builder nick(String nick) {
      this.instance.nick(nick);
      return this;
    }
    
    public PlayerDto.Builder connected(Boolean connected) {
      this.instance.connected(connected);
      return this;
    }
    
    public PlayerDto.Builder role(PlayerRoleDto role) {
      this.instance.role(role);
      return this;
    }
    
    public PlayerDto.Builder cardCount(Integer cardCount) {
      this.instance.cardCount(cardCount);
      return this;
    }
    
    /**
    * returns a built PlayerDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PlayerDto build() {
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
  public static PlayerDto.Builder builder() {
    return new PlayerDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PlayerDto.Builder toBuilder() {
    PlayerDto.Builder builder = new PlayerDto.Builder();
    return builder.copyOf(this);
  }

}

