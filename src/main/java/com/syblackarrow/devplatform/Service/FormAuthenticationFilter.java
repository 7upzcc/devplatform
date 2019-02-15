package com.syblackarrow.devplatform.Service;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request) ;
        String password = getPassword(request) ;
        return new UsernamePasswordToken(username,password) ;
    }

    protected String getUsername(ServletRequest request) {
        String username = super.getUsername(request);
        if (StringUtils.isEmpty(username)) {
            username = request.getAttribute(getUsernameParam()).toString() ;
        }
        return username;
    }

    @Override
    protected String getPassword(ServletRequest request) {
        String password = super.getPassword(request);
        if (StringUtils.isEmpty(password)) {
            password = request.getAttribute(getPasswordParam()).toString() ;
        }
        return password;
    }
}
