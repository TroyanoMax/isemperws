package org.isemper.com.institutosemper.repository;

import org.isemper.com.institutosemper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
