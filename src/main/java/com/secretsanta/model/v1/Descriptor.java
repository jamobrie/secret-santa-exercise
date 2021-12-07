package com.secretsanta.model.v1;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Descriptor
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-12-03T16:42:53.786Z[Europe/London]")
public class Descriptor   {
  @JsonProperty("code")
  private String code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("traceId")
  private String traceId;

  @JsonProperty("helpUrl")
  private String helpUrl;

  public Descriptor code(String code) {
    this.code = code;
    return this;
  }

  /**
   * unique code for the specific messsage
   * @return code
  */
  @ApiModelProperty(required = true, value = "unique code for the specific messsage")
  @NotNull


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Descriptor message(String message) {
    this.message = message;
    return this;
  }

  /**
   * message describing specifc error
   * @return message
  */
  @ApiModelProperty(required = true, value = "message describing specifc error")
  @NotNull


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Descriptor traceId(String traceId) {
    this.traceId = traceId;
    return this;
  }

  /**
   * traceId to retrieve the message within the traces
   * @return traceId
  */
  @ApiModelProperty(value = "traceId to retrieve the message within the traces")


  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public Descriptor helpUrl(String helpUrl) {
    this.helpUrl = helpUrl;
    return this;
  }

  /**
   * URL of the documentation
   * @return helpUrl
  */
  @ApiModelProperty(value = "URL of the documentation")


  public String getHelpUrl() {
    return helpUrl;
  }

  public void setHelpUrl(String helpUrl) {
    this.helpUrl = helpUrl;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Descriptor descriptor = (Descriptor) o;
    return Objects.equals(this.code, descriptor.code) &&
        Objects.equals(this.message, descriptor.message) &&
        Objects.equals(this.traceId, descriptor.traceId) &&
        Objects.equals(this.helpUrl, descriptor.helpUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, traceId, helpUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Descriptor {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
    sb.append("    helpUrl: ").append(toIndentedString(helpUrl)).append("\n");
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
}

