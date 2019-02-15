package com.syblackarrow.devplatform.Service;

import com.syblackarrow.devplatform.Dao.UserDao;
import com.syblackarrow.devplatform.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao ;

    public User getUser(){
        return userDao.getUser() ;
    }

    public User getUserByName(String username){
        return userDao.getUserByName(username) ;
    }

    public User getUserInfoByName(String username){
        return userDao.getUserInfoByName(username) ;
    }
}
