package com.syblackarrow.devplatform.ApiController;

import cn.hutool.json.JSONUtil;
import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService ;

    @RequestMapping("/index")
    public String userIndex() {
        return " test ";
    }

    @RequestMapping("/api/user/getUserInfo")
    @ResponseBody
    @RequiresPermissions("puser")
    public String getUserInfo(){
        return  JSONUtil.toJsonStr(userService.getCurrentUser());
    }
}
