package org.isemper.com.institutosemper.security.repository;

import org.isemper.com.institutosemper.security.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByDescription(String description);
}
