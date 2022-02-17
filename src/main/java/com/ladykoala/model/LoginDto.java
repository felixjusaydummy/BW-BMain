package com.ladykoala.model;

import com.ladykoala.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginDto {
    private final UserDetails userDetails;
    private long userId;

    public LoginDto(UserDetails userDetails, long userDao) {
        this.userDetails = userDetails;
        this.userId = userDao;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public long getUserId() {
        return userId;
    }
}
