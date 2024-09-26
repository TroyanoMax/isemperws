package org.isemper.com.isemperws.service;

import org.isemper.com.isemperws.model.dto.UserResponse;
import org.isemper.com.isemperws.model.dto.UserDTO;
import org.isemper.com.isemperws.model.projection.StudentDataProjection;

public interface UsersService {

    UserResponse userSignup(UserDTO userDTO);

    StudentDataProjection verifyStudentRegistration(String codigo);

}
