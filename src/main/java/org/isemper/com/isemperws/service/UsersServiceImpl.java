package org.isemper.com.isemperws.service;

import lombok.extern.log4j.Log4j2;
import org.isemper.com.isemperws.exception.GeneralServiceException;
import org.isemper.com.isemperws.model.dto.UserResponse;
import org.isemper.com.isemperws.model.dto.UserDTO;
import org.isemper.com.isemperws.model.projection.StudentDataProjection;
import org.isemper.com.isemperws.repository.instituto.StudentRepository;
import org.isemper.com.isemperws.security.model.entity.UserEntity;
import org.isemper.com.isemperws.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse userSignup(UserDTO userDTO) {
        log.info("User user with username: {}", userDTO.getUsername());

        var users = new UserResponse();
        UserEntity userSaved;

        var user = modelMapper.map(userDTO, UserEntity.class);
        var alumno = this.verifyStudentRegistration(user.getUsername());

        user.setCodAlu(Integer.valueOf(alumno.getCodigo()));

        try {

            userSaved = userRepository.save(user);

        } catch (DataAccessException e) {
            log.error("Error while signing up: {}", e.getMessage());
            throw new GeneralServiceException("Error while signin up: " + e.getMessage());
        }

        UserDTO userResponse = modelMapper.map(userSaved, UserDTO.class);

        users.setUsers(List.of(userResponse));
        users.setAlumno(List.of(alumno));

        return users;

    }

    /**
     * Verifica si el alumno es válido para registrarse.
     * @param codigo parametro de búsqueda.
     * @return - Datos básicos del alumno.
     * */
    @Override
    public StudentDataProjection verifyStudentRegistration(String codigo) {
        log.info("Validating user with code: {}", codigo);

        return studentRepository.findAlumnosByCodAluNative(codigo)
                .stream().findFirst()
                .orElseThrow(
                        () -> new GeneralServiceException("Student not found with code: " + codigo)
                );
    }

}
