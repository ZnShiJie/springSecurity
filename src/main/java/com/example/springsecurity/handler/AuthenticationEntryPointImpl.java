package com.example.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ResponseResult<Object> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "权限认证失败");
        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, jsonString);


    }
}
