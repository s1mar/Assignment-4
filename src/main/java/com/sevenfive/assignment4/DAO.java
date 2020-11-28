package com.sevenfive.assignment4;


import com.sevenfive.assignment4.auth.models.User;

import java.util.HashMap;
import java.util.Map;

//The Data Access Object
public class DAO {

    private volatile static DAO mInstance;

    public synchronized static DAO getInstance(){
        if(mInstance == null){
            mInstance = new DAO();
            mInstance.users_passwords.put("james","bond");
            mInstance.users_passwords.put("simar","khanna");
            mInstance.users_passwords.put("mallory","smaster");
            mInstance.users_roles.put("james","customer");
            mInstance.users_roles.put("mallory","vendor");
            mInstance.users_roles.put("simar","admin");

        }
        return mInstance;
    }

    private DAO(){}

    private final Map <String,String> users_passwords = new HashMap<>();
    private final Map<String,String> users_roles = new HashMap<>();


    public boolean searchForUser(String user,String password){
        if (users_passwords.containsKey(user)){
            String assocPass = users_passwords.get(user);
            return assocPass.equals(password);
        }
        return false;
    }

   public String getRole(String username){
        return users_roles.getOrDefault(username,"");
   }

}
