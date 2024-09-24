package org.isemper.com.isemperws.security.repository;

import org.isemper.com.isemperws.security.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
