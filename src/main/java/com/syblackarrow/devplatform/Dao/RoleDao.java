package com.syblackarrow.devplatform.Dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleDao {
    List<String> getRoleListByUsername(@Param("username")String username) ;
}
