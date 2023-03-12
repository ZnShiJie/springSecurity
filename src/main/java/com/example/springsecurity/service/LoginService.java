package com.example.springsecurity.service;

import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.domain.entity.User;

public interface LoginService  {

    ResponseResult login(User user);

    ResponseResult logout();
}
