package org.isemper.com.institutosemper.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO {

    @JsonProperty("roleEntityId")
    private Long roleId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("code")
    private String code;

}
