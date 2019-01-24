package com.syblackarrow.devplatform.Dao;

import com.syblackarrow.devplatform.Model.User;
import org.springframework.stereotype.Component;


@Component
public interface UserDao {
    User getUser() ;
    User getUserByName(String username) ;
}
