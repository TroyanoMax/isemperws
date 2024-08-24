package org.isemper.com.institutosemper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Entity
@Table(name = "ROLES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends AuditEntity {

    /** * The serial Version UID. */
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

}
