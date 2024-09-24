package org.isemper.com.institutosemper.service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.isemper.com.institutosemper.exception.GeneralServiceException;
import org.isemper.com.institutosemper.model.dto.UserResponse;
import org.isemper.com.institutosemper.model.dto.UserDTO;
import org.isemper.com.institutosemper.repository.instituto.AlumnoRepository;
import org.isemper.com.institutosemper.security.model.entity.UserEntity;
import org.isemper.com.institutosemper.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final AlumnoRepository alumnoRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, AlumnoRepository alumnoRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.alumnoRepository = alumnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse userSignup(UserDTO userDTO) {

        var users = new UserResponse();

        var user = modelMapper.map(userDTO, UserEntity.class);

        this.validarAlumno("136");

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

    @Transactional
    protected void validarAlumno(String codigo) {
        log.info("Validating user: {}", codigo);

        alumnoRepository.findByCodigo(codigo).ifPresent(alumno -> {
            log.info("Alumno: {}", alumno.getApellido());
        });

    }

}
