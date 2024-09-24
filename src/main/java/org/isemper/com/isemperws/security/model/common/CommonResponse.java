/**
 * 
 */
package org.isemper.com.isemperws.security.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/** * @author Claudio R. González. */
@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse implements Serializable {

  /** * The constant serialVersionUID. */
  @Serial
  private static final long serialVersionUID = 1L;

  /** * Status Message. */
  @JsonProperty("status")
  private transient StatusMessage status;

  /**
   * Instantiates a new Common response.
   *
   * @param success      the success
   * @param httpStatus   the http status
   * @param errorCode    the error code
   * @param errorMessage the error message
   * @param message      the message
   */
  public CommonResponse(boolean success, Integer httpStatus, String errorCode, String errorMessage, String message) {
    super();
    this.status = new StatusMessage(success, httpStatus, errorCode, errorMessage, message);

  }

}
