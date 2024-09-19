package org.isemper.com.institutosemper.security.component;

import org.isemper.com.institutosemper.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UsersService userService;

    @Autowired
    public LoginSuccessListener(UsersService usuarioService) {
        this.userService = usuarioService;
    }

    @Override
    @TransactionalEventListener
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        userService.incrementLoginCount(username);
    }
}
