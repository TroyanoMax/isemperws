package org.isemper.com.isemperws.security.repository;

import org.isemper.com.isemperws.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
