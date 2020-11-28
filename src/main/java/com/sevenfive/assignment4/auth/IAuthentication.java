package com.sevenfive.assignment4.auth;

import com.sevenfive.assignment4.auth.models.User;

public interface IAuthentication {

    boolean verifyCredentials(String token,String username,String password);
    void logAnnonUser(String token);
    boolean isLoggedIn(String token);
    void authenticateUser(String token,boolean isAuthenticated);
    User getUser(String token);
    void invalidateSession(String token);
    public boolean isAuthorized(String token, Auth.Role targetRole);
}
