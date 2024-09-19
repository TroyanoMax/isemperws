package org.isemper.com.institutosemper.service;

import lombok.extern.log4j.Log4j2;
import org.isemper.com.institutosemper.exception.GeneralServiceException;
import org.isemper.com.institutosemper.model.dto.UserResponse;
import org.isemper.com.institutosemper.model.dto.UserDTO;
import org.isemper.com.institutosemper.security.model.entity.UserEntity;
import org.isemper.com.institutosemper.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse userSignup(UserDTO userDTO) {

        var users = new UserResponse();

        var user = modelMapper.map(userDTO, UserEntity.class);

        try {

            user = userRepository.save(user);

        } catch (DataAccessException e) {
            log.error("Error while signing up: {}", e.getMessage());
            throw new GeneralServiceException("Error while signin up: " + e.getMessage());
        }

        UserDTO userSaved = modelMapper.map(user, UserDTO.class);

        users.setUsers(List.of(userSaved));

        return users;

    }

    public void incrementLoginCount(String username) {
        Optional<UserEntity> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setCantAcc(user.getCantAcc() + 1);
            userRepository.save(user);
        }
    }

}
