package org.isemper.com.institutosemper.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ROLES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

}
