package org.isemper.com.isemperws.security.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;

/**
 * The type Generic response dto.
 *
 * @param <T> the type parameter
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> extends CommonResponse {

    /** * */
    @Serial
    private static final long serialVersionUID = 1L;

    /** * The Body. */
    @JsonProperty("body")
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
    public GenericResponse(
            boolean success,
            Integer httpStatus,
            String errorCode,
            String errorMessage,
            String message,
            T body
    ) {
        super(success, httpStatus, errorCode, errorMessage, message);
        this.body = body;
    }

}
