package org.isemper.com.isemperws.security.service;

public interface SecurityService {

    void incrementLoginCount(String username);

    void loginAuditor(String username, String ipAddress);

}
