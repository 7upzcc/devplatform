package com.syblackarrow.devplatform.Config;

import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Service.RoleService;
import com.syblackarrow.devplatform.Service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        // 从数据库获取对应用户名密码的用户
        User userList = userService.getUserByName(user.getUsername());
        if (userList != null) {
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userList, //用户
                    userList.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if(principal instanceof User ){
            User userLogin = (User) principal;
            List<String> roles = roleService.getRoleListByUsername(userLogin.getUsername());
            authorizationInfo.addRoles(roles);
            Set<String> permissions = roleService.getPermissionByUsername(userLogin.getUsername()) ;
            authorizationInfo.addStringPermissions(permissions);
        }
        return authorizationInfo;
    }

}
