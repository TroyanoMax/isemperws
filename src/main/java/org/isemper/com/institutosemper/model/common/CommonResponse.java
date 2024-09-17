/**
 * 
 */
package org.isemper.com.institutosemper.model.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class CommonResponse implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Status Message.
   */
  @Setter
  @Getter
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
