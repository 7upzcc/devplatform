package com.syblackarrow.devplatform.Dao;

import com.syblackarrow.devplatform.Model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface UserDao {
    User getUser() ;
    User getUserByName(String username) ;
    Map<String,Object> getUserInfoByName(String username) ;
    User getUserInfoById(String id) ;
}
