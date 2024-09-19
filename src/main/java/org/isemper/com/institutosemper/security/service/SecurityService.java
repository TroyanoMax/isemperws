package org.isemper.com.institutosemper.security.service;

public interface SecurityService {

    void incrementLoginCount(String username);

    void loginAuditor(String username, String ipAddress);

}
