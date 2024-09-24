package org.isemper.com.isemperws.service;

import lombok.extern.log4j.Log4j2;
import org.isemper.com.isemperws.exception.GeneralServiceException;
import org.isemper.com.isemperws.model.dto.AlumnoDTO;
import org.isemper.com.isemperws.model.dto.UserResponse;
import org.isemper.com.isemperws.model.dto.UserDTO;
import org.isemper.com.isemperws.model.entity.Alumno;
import org.isemper.com.isemperws.repository.instituto.AlumnoRepository;
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
        UserEntity userSaved;

        var user = modelMapper.map(userDTO, UserEntity.class);
        var alumno = this.validarAlumno(user.getCodAlu());

        try {

            userSaved = userRepository.save(user);

        } catch (DataAccessException e) {
            log.error("Error while signing up: {}", e.getMessage());
            throw new GeneralServiceException("Error while signin up: " + e.getMessage());
        }

        UserDTO userResponse = modelMapper.map(userSaved, UserDTO.class);
        AlumnoDTO alumnoResponse = modelMapper.map(alumno, AlumnoDTO.class);

        users.setUsers(List.of(userResponse));
        users.setAlumno(List.of(alumnoResponse));

        return users;

    }

    private Alumno validarAlumno(Integer codigo) {
        log.info("Validating user with code: {}", codigo);

        String code = codigo.toString();

        return alumnoRepository.findByCodigo(code)
                .map(alumno -> {
                    log.info("Alumno found: {}", alumno.getCodigo());
                    return alumno;
                })
                .orElseThrow(
                        () -> new RuntimeException("Alumno not found with code: " + codigo)
                );
    }

}
