package com.lms.service;

import com.lms.dataaccess.Auth;
import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.User;
import com.lms.utils.Constants;
import com.lms.utils.InvalidCredentialException;

import java.util.Optional;

public class LoginServiceImpl implements LoginService{

    private DataAccess dataAccess;

    public LoginServiceImpl(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public Auth login(String username, String password) {
        User loginUser = dataAccess.readUserMap().entrySet().stream()
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
