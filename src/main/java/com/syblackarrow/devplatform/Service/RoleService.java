package com.syblackarrow.devplatform.Service;


import com.syblackarrow.devplatform.Dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao ;

    public List<String> getRoleListByUsername(String username){
        return roleDao.getRoleListByUsername(username) ;
    }

    public Set<String> getPermissionByUsername(String username){
        return roleDao.getPermissionByUsername(username) ;
    }
}
