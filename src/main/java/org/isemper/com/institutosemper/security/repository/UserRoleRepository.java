package org.isemper.com.institutosemper.security.repository;

import org.isemper.com.institutosemper.security.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
