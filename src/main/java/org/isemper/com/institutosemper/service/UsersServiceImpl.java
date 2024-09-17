package org.isemper.com.institutosemper.service;

import jdk.jshell.spi.ExecutionControl;
import lombok.extern.log4j.Log4j2;
import org.isemper.com.institutosemper.exception.GeneralServiceException;
import org.isemper.com.institutosemper.model.dto.UserResponse;
import org.isemper.com.institutosemper.model.dto.UserDTO;
import org.isemper.com.institutosemper.security.model.entity.UserEntity;
import org.isemper.com.institutosemper.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.ArrayList;

@Service
@Log4j2
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse userSignup(UserDTO userDTO) {

        var users = new UserResponse();


        try {



        } catch (DataAccessException e) {
            log.error("Error while signing up: {}", e.getMessage());
            throw new GeneralServiceException("Error while signin up" + e.getMessage());
        }

        return users;

    }

}
