package com.syblackarrow.devplatform.Dao;

import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Model.UserSettings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface UserDao {
    User getUser() ;
    User getUserByName(String username) ;
    Map<String,Object> getUserInfoByName(String username) ;
    User getUserInfoById(String id) ;
    List<UserSettings> getUserSettingsByUserId(String userId) ;
}
