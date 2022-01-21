package com.lms.service;

import com.lms.dataaccess.Auth;
import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.User;
import com.lms.utils.Constants;
import com.lms.exception.InvalidCredentialException;

import java.util.Map;

public class LoginServiceImpl implements LoginService{

    private DataAccess dataAccess;

    public LoginServiceImpl(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public Auth login(String username, String password) {
        Map<String,User> users = dataAccess.readUserMap();
        User loginUser = users.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(user -> user.getId().equals(username) && user.getPassword().equals(password))
                .findAny().orElseThrow(() -> new InvalidCredentialException(Constants.LOGIN_INVALID_INFO));
        return loginUser.getAuthorization();
    }

    @Override
    public boolean isInvalid(String username, String password) {
        return username.trim().isEmpty() || password.trim().isEmpty();

    }
}
