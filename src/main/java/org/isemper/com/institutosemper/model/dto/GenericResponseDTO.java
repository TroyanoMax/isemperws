package org.isemper.com.institutosemper.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.isemper.com.institutosemper.model.common.CommonResponse;

/**
 * The type Generic response dto.
 *
 * @param <T> the type parameter
 */

@EqualsAndHashCode(callSuper = false)
public class GenericResponseDTO<T> extends CommonResponse {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * The Body.
   */
  @Setter
  @Getter
  private transient T body;

  /**
   * Instantiates a new Generic response dto.
   *
   * @param success      the success
   * @param httpStatus   the http status
   * @param errorCode    the error code
   * @param errorMessage the error message
   * @param message      the message
   * @param body         the body
   */
  public GenericResponseDTO(boolean success, Integer httpStatus, String errorCode, String errorMessage,
                            String message, T body) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.body = body;
  }
}
