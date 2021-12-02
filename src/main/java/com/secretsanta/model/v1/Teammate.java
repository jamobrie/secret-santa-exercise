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
 * Teammate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-12-02T22:10:35.258Z[Europe/London]")
public class Teammate   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("secondName")
  private String secondName;

  @JsonProperty("secretSanta")
  private String secretSanta;

  @JsonProperty("yearOfSecretSanta")
  private Integer yearOfSecretSanta;

  public Teammate id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "1", value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Teammate firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  */
  @ApiModelProperty(example = "James", value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Teammate secondName(String secondName) {
    this.secondName = secondName;
    return this;
  }

  /**
   * Get secondName
   * @return secondName
  */
  @ApiModelProperty(example = "O'Brien", value = "")


  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public Teammate secretSanta(String secretSanta) {
    this.secretSanta = secretSanta;
    return this;
  }

  /**
   * Get secretSanta
   * @return secretSanta
  */
  @ApiModelProperty(example = "Tom", value = "")


  public String getSecretSanta() {
    return secretSanta;
  }

  public void setSecretSanta(String secretSanta) {
    this.secretSanta = secretSanta;
  }

  public Teammate yearOfSecretSanta(Integer yearOfSecretSanta) {
    this.yearOfSecretSanta = yearOfSecretSanta;
    return this;
  }

  /**
   * Get yearOfSecretSanta
   * @return yearOfSecretSanta
  */
  @ApiModelProperty(example = "1", value = "")


  public Integer getYearOfSecretSanta() {
    return yearOfSecretSanta;
  }

  public void setYearOfSecretSanta(Integer yearOfSecretSanta) {
    this.yearOfSecretSanta = yearOfSecretSanta;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Teammate teammate = (Teammate) o;
    return Objects.equals(this.id, teammate.id) &&
        Objects.equals(this.firstName, teammate.firstName) &&
        Objects.equals(this.secondName, teammate.secondName) &&
        Objects.equals(this.secretSanta, teammate.secretSanta) &&
        Objects.equals(this.yearOfSecretSanta, teammate.yearOfSecretSanta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, secondName, secretSanta, yearOfSecretSanta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Teammate {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    secondName: ").append(toIndentedString(secondName)).append("\n");
    sb.append("    secretSanta: ").append(toIndentedString(secretSanta)).append("\n");
    sb.append("    yearOfSecretSanta: ").append(toIndentedString(yearOfSecretSanta)).append("\n");
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

