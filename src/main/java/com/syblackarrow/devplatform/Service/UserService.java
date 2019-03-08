package com.syblackarrow.devplatform.Service;

import com.syblackarrow.devplatform.Dao.UserDao;
import com.syblackarrow.devplatform.Model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao ;

    /**
     * 获得当前用户
     * @return 当前用户的对象，不包含密码信息
     */
    public Map<String,Object> getCurrentUser(){
        User user  = (User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal() ;
        return getUserInfoByName(user.getUsername()) ;
    }

    public User getUser(){
        return userDao.getUser() ;
    }

    public User getUserByName(String username){
        return userDao.getUserByName(username) ;
    }

    public Map<String,Object> getUserInfoByName(String username){
        return userDao.getUserInfoByName(username) ;
    }

    public User getUserInfoById(String id){
        return userDao.getUserInfoById(id) ;
    }
}
