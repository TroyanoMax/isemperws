package org.isemper.com.institutosemper.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO extends AuditDTO {

    @JsonProperty("roleEntityId")
    private Long roleId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("code")
    private String code;

}
