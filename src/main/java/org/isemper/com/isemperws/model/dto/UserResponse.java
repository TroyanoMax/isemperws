package org.isemper.com.isemperws.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.isemper.com.isemperws.model.entity.Alumno;

import java.util.List;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends AuditDTO {

    @JsonProperty("users")
    private List<UserDTO> users;

    @JsonProperty("userString")
    private String userString;

    @JsonProperty("alumno")
    private List<AlumnoDTO> alumno;

}
