package com.sevenfive.assignment4.auth;

import com.sevenfive.assignment4.DAO;
import com.sevenfive.assignment4.auth.models.User;

import java.util.HashMap;
import java.util.Map;

public class Auth implements IAuthentication {

    private static final String TAG = Auth.class.getSimpleName();
    private final Map<String, User> map = new HashMap<>();
    private static volatile Auth mInstance;

    public synchronized static Auth getInstance(){
        if(mInstance == null){
            mInstance = new Auth();
        }
        return mInstance;
    }

    private Auth() {
        super();
    }

    @Override
    public boolean verifyCredentials(String token,String username, String password) {
        //fill the user
        User user = map.getOrDefault(token,null);
        user.setUsername(username);
        user.setRole(DAO.getInstance().getRole(username));
        return DAO.getInstance().searchForUser(username,password);
    }

    @Override
    public void invalidateSession(String token) {
        map.remove(token);
    }

    @Override
    public void logAnnonUser(String token) {
        User user = new User();
        user.setToken(token);
        map.put(token,user);
    }

    @Override
    public boolean isLoggedIn(String token) {

        boolean isLoggedIn = false;
        User usr =  map.getOrDefault(token,null); //this means it's an anonymous user
        if(usr!=null){
            isLoggedIn = usr.isAuthenticated();
        }

        return isLoggedIn;
    }

    @Override
    public void authenticateUser(String token, boolean isAuthenticated) {
        User user = map.getOrDefault(token,null);
        user.setAuthenticated(true);
    }

    @SuppressWarnings({"NullPointerException", "ConstantConditions"})
    public boolean isAuthorized(String token, Role targetRole){

        User user = getUser(token);
        String userRole = user.getRole();
        Integer userPrivilege = Role.privilegeLevel(userRole);
        try{
            return userPrivilege == Role.ADMIN.privilegeLevel || userPrivilege.equals(targetRole.privilegeLevel);
        }
        catch (Exception ex){
            return false;
        }


    }

    @Override
    public User getUser(String token) {
        return map.getOrDefault(token,null);
    }

public enum Role{
        ADMIN(1,"admin"),
        VENDOR(3,"vendor"),
        CUSTOMER(2,"customer");
        int privilegeLevel;
        String role;
        private Role(int v,String role){
            privilegeLevel = v;
            this.role = role;
        }
        public int getPrivilegeLevel(){
            return privilegeLevel;
        }

    @Override
    public String toString() {
        return role;
    }

    public static Integer privilegeLevel(String role){
            switch (role) {
                case "admin":
                    return ADMIN.privilegeLevel;
                case "customer":
                    return CUSTOMER.privilegeLevel;
                case "vendor":
                    return VENDOR.privilegeLevel;
            }
            return null;
        }
    }
}


