
package org.isemper.com.isemperws.security.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/** * @author Claudio R. González */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusMessage {

  /** * The Success. */
  @JsonProperty("success")
  private boolean success;

  /** * The Http status. */
  @JsonProperty("httpStatus")
  private Integer httpStatus;

  /** * The Error code. */
  @JsonProperty("errorCode")
  private String errorCode;

  /** * The Error message. */
  @JsonProperty("errorMessage")
  private String errorMessage;

  /** * The Message. */
  @JsonProperty("message")
  private String message;

}
