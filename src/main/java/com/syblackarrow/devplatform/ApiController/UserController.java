package com.syblackarrow.devplatform.ApiController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @RequestMapping("/index")
    public String userIndex(){
        return " test ";
    }
}
