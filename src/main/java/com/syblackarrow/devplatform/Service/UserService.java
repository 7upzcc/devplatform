package com.syblackarrow.devplatform.Service;

import com.alibaba.druid.util.StringUtils;
import com.syblackarrow.devplatform.Core.UserSettingsCode;
import com.syblackarrow.devplatform.Dao.UserDao;
import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Model.UserSettings;
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
     * -----------------------------------
     * 获得的用户信息包含以下字段，以后随需求增加
     * 用户ID： id
     * 用户名称（登录名）：username
     * 用户描述信息：content
     * ------------------------------------
     * @return 当前用户的对象，不包含密码信息
     */
    public Map<String,Object> getCurrentUser(){
        User user  = (User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal() ;
        return getUserInfoByName(user.getUsername()) ;
    }

    /**
     * 获得当前用户的ID
     * @return 当前用户的ID
     */
    public String getCurrentUserId(){
        return (String)getCurrentUser().get("id") ;
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

    /**
     * 获得用户的设置信息
     * @return
     */
    public List<UserSettings> getUserSettings(){
        String userId = getCurrentUserId() ;
        return userDao.getUserSettingsByUserId(userId) ;
    }

    /**
     * 获得用户上传设置信息
     * @return 判断结果
     */
    public boolean getUserUploadSetting(){
        List<UserSettings> userSettingsList = getUserSettings() ;
        for(UserSettings userSettings : userSettingsList){
            if(userSettings.getSettingKey().equals(UserSettingsCode.IS_UPLOAD.getName())){
                return ( !StringUtils.isEmpty(userSettings.getSettingValue()) && userSettings.getSettingValue().equals("1") ) ;
            }
        }
        return false ;
    }

    /**
     * 获得用户的目录限制大小
     * @return 目录大小，单位MB
     */
    public Integer getUserFileSize(){
        List<UserSettings> userSettingsList = getUserSettings() ;
        for(UserSettings userSettings : userSettingsList){
            if(userSettings.getSettingKey().equals(UserSettingsCode.FILE_SIZE.getName())){
               return Integer.parseInt(userSettings.getSettingValue()) ;
            }
        }
        return 0 ;
    }
}
