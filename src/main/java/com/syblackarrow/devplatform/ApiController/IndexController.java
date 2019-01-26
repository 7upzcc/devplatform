package com.syblackarrow.devplatform.ApiController;

import com.alibaba.fastjson.JSON;
import com.syblackarrow.devplatform.Core.ControllerReturn;
import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Service.RoleService;
import com.syblackarrow.devplatform.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String index() {
        return "welcome index";
    }

    @RequestMapping("/getUser")
    public String getUser() {
        User user = userService.getUser();
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String username, @RequestParam(required = true) String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        List<String> roleList = roleService.getRoleListByUsername(username);
        if (roleList.contains("user")) {
            return ControllerReturn.SUCCESS("欢迎登陆");
        } else if (roleList.contains("admim")) {
            return ControllerReturn.SUCCESS("欢迎来到管理员页面");
        } else {
            return ControllerReturn.FAIL("您没有登陆权限");
        }
    }
}
