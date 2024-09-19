package org.isemper.com.institutosemper.security.repository;

import org.isemper.com.institutosemper.security.model.entity.LoginAuditor;
import org.isemper.com.institutosemper.security.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAuditorRepository extends JpaRepository<LoginAuditor, Long> {
    LoginAuditor findByUserId(String userId);
}
