package com.lms.service;

import com.lms.dataaccess.Auth;
import com.lms.dataaccess.User;

public interface LoginService {

    Auth login(String username, String password);

    boolean isInvalid(String username, String password);
}
