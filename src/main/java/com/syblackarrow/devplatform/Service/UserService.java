package com.syblackarrow.devplatform.Service;

import com.syblackarrow.devplatform.Dao.UserDao;
import com.syblackarrow.devplatform.Model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao ;

    /**
     * 获得当前用户
     * @return 当前用户的对象，不包含密码信息
     */
    public User getCurrentUser(){
        String username  = SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal().toString() ;
        return getUserInfoByName(username) ;
    }

    public User getUser(){
        return userDao.getUser() ;
    }

    public User getUserByName(String username){
        return userDao.getUserByName(username) ;
    }

    public User getUserInfoByName(String username){
        return userDao.getUserInfoByName(username) ;
    }
    public User getUserInfoById(String id){
        return userDao.getUserInfoById(id) ;
    }
}
