package org.isemper.com.institutosemper.service;

import lombok.extern.log4j.Log4j2;
import org.isemper.com.institutosemper.model.dto.UserResponse;
import org.isemper.com.institutosemper.model.dto.UserDTO;
import org.isemper.com.institutosemper.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        users.setUsers(new ArrayList<>());
        users.getUsers().add(userDTO);
        return users;

    }

}
