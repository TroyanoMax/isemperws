package org.isemper.com.institutosemper.repository;

import org.isemper.com.institutosemper.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
