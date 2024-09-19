package org.isemper.com.institutosemper.service;

import org.isemper.com.institutosemper.model.dto.UserResponse;
import org.isemper.com.institutosemper.model.dto.UserDTO;

public interface UsersService {

    UserResponse userSignup(UserDTO userDTO);

    void incrementLoginCount(String username);

}
