package org.isemper.com.institutosemper.security.repository;

import org.isemper.com.institutosemper.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
