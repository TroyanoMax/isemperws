package org.isemper.com.institutosemper.security.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.isemper.com.institutosemper.security.model.entity.LoginAuditor;
import org.isemper.com.institutosemper.security.model.entity.UserEntity;
import org.isemper.com.institutosemper.security.repository.LoginAuditorRepository;
import org.isemper.com.institutosemper.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Log4j2
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    private final LoginAuditorRepository loginAuditorRepository;

    @Autowired
    public SecurityServiceImpl(UserRepository userRepository,
                               LoginAuditorRepository loginAuditorRepository
    ) {
        this.userRepository = userRepository;
        this.loginAuditorRepository = loginAuditorRepository;
    }

    @Override
    public void incrementLoginCount(String username) {
        Optional<UserEntity> userOptional = Optional.ofNullable(
                userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setCantAcc(user.getCantAcc() + 1);
            userRepository.save(user);
        }
    }

    @Override
    public void loginAuditor(String username, String ipAddress) {
        Optional<UserEntity> userOptional = Optional.ofNullable(
                userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            var loginAuditor = LoginAuditor.builder()
                    .id(null)
                    .userId(userOptional.get().getCodAlu().toString())
                    .longinTime(new Date())
                    .ipAddress(ipAddress)
                    .build();
            loginAuditorRepository.save(loginAuditor);
        }
    }

}
