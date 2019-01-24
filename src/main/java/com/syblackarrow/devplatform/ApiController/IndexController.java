package com.syblackarrow.devplatform.ApiController;

import com.alibaba.fastjson.JSON;
import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @Autowired
    private UserService userService ;

    @RequestMapping("/")
    public String index(){
        return "welcome index" ;
    }

    @RequestMapping("/getUser")
    public String getUser(){
        User user = userService.getUser() ;
        return JSON.toJSONString(user) ;
    }
}
