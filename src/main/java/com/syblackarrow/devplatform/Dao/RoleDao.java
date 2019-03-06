package com.syblackarrow.devplatform.Dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface RoleDao {
    List<String> getRoleListByUsername(@Param("username")String username) ;
    Set<String> getPermissionByUsername(@Param("username")String username) ;
}
