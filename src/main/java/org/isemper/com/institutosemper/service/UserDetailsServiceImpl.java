package org.isemper.com.institutosemper.service;

import org.isemper.com.institutosemper.entity.UserEntity;
import org.isemper.com.institutosemper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Obtener el usuario de la base de datos
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username);
        }

        // Convertir los roles a GrantedAuthority
        Set<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getDescription()))
                .collect(Collectors.toSet());

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
