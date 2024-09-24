package org.isemper.com.isemperws.security.repository;

import org.isemper.com.isemperws.security.model.entity.LoginAuditor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAuditorRepository extends JpaRepository<LoginAuditor, Long> {
    LoginAuditor findByUserId(String userId);
}
