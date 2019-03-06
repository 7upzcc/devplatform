package com.syblackarrow.devplatform.ApiController;

import cn.hutool.json.JSONUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
            Map<String,Object> map = new HashMap<>();
            map.put("code", "-999");
            map.put("message", "无权限");
            return JSONUtil.toJsonStr(map) ;
    }

    @ExceptionHandler({UnauthenticatedException.class})
    public String unAuthenticatedException(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        map.put("code", "-999");
        map.put("message", "你没有登录，请先登录");
        return JSONUtil.toJsonStr(map) ;
    }
}
