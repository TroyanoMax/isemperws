package org.isemper.com.institutosemper.repository;

import org.isemper.com.institutosemper.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByDescription(String description);
}
