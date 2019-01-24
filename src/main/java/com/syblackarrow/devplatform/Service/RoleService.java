package com.syblackarrow.devplatform.Service;


import com.syblackarrow.devplatform.Dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao ;

    public List<String> getRoleListByUsername(String username){
        return roleDao.getRoleListByUsername(username) ;
    }
}
