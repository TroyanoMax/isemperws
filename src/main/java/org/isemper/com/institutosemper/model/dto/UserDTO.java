package org.isemper.com.institutosemper.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.isemper.com.institutosemper.security.model.entity.RoleEntity;

import java.io.Serial;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AuditDTO {

    /** * The serial Version UID. */
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("codAlu")
    private Integer codAlu;

    @JsonProperty("email")
    private String email;

    @JsonProperty("carrera")
    private String carrera;

    @JsonProperty("cantAcc")
    private Integer cantAcc;

    @JsonProperty("roles")
    private List<RoleDTO> roles;

}
