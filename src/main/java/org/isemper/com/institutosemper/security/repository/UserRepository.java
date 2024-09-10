package org.isemper.com.institutosemper.security.repository;

import org.isemper.com.institutosemper.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
