package org.isemper.com.institutosemper.repository;

import org.isemper.com.institutosemper.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByDescription(String description);
}
