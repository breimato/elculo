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
 * WsErrorDto
 */

@JsonTypeName("WsError")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-18T00:12:02.220634600+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class WsErrorDto {

  private String code;

  private String message;

  public WsErrorDto code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   */
  
  @Schema(name = "code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public WsErrorDto message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WsErrorDto wsError = (WsErrorDto) o;
    return Objects.equals(this.code, wsError.code) &&
        Objects.equals(this.message, wsError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WsErrorDto {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

    private WsErrorDto instance;

    public Builder() {
      this(new WsErrorDto());
    }

    protected Builder(WsErrorDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(WsErrorDto value) { 
      this.instance.setCode(value.code);
      this.instance.setMessage(value.message);
      return this;
    }

    public WsErrorDto.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public WsErrorDto.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built WsErrorDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public WsErrorDto build() {
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
  public static WsErrorDto.Builder builder() {
    return new WsErrorDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public WsErrorDto.Builder toBuilder() {
    WsErrorDto.Builder builder = new WsErrorDto.Builder();
    return builder.copyOf(this);
  }

}

