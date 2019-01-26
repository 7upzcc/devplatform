package com.syblackarrow.devplatform.Config;

import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Service.RoleService;
import com.syblackarrow.devplatform.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roleList = roleService.getRoleListByUsername(username);
        Set<String> roleSet = new HashSet<>();
        roleSet.addAll(roleList);
        info.setRoles(roleSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByName(token.getUsername());
        if (user == null) {
            throw new AccountException("用户不存在");
        }
        if (!user.getPassword().equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        token.setRememberMe(true);
        return new SimpleAuthenticationInfo(token.getPrincipal(), user.getPassword(), getName());
    }
}
