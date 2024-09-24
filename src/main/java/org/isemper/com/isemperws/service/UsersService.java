package org.isemper.com.isemperws.service;

import org.isemper.com.isemperws.model.dto.UserResponse;
import org.isemper.com.isemperws.model.dto.UserDTO;

public interface UsersService {

    UserResponse userSignup(UserDTO userDTO);

}
