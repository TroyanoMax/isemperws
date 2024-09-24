package org.isemper.com.isemperws.security.model.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "users_roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRoleEntity extends AuditEntity {

    @EmbeddedId
    private UserRoleId id;

    @Column(name = "DESCRIPTION")
    private String description;

}
