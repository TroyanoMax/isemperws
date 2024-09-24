package org.isemper.com.isemperws.security.repository;

import org.isemper.com.isemperws.security.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByDescription(String description);
}
