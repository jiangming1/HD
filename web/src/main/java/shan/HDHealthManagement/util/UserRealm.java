package shan.HDHealthManagement.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import shan.HDHealthManagement.po.Jurisdiction;
import shan.HDHealthManagement.po.RoleJurisdiction;
import shan.HDHealthManagement.po.User;
import shan.HDHealthManagement.po.UserRole;
import shan.HDHealthManagement.service.RoleService;
import shan.HDHealthManagement.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Resource(name="userService")
    private UserService userService;
	@Resource(name="roleService")
	private RoleService roleService;

    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleNames = new HashSet<String>();
        Set<String> permissionNames = new HashSet<String>();
        User user = userService.findByUsername(userName);
        List<UserRole> userRoles = userService.findRoleById(user.getId());
        for (UserRole userRole : userRoles) {
			List<RoleJurisdiction> roleJurisdictions = roleService.findRoleJurisdictions(userRole.getRoleId());
			for (RoleJurisdiction roleJurisdiction : roleJurisdictions) {
				Jurisdiction jurisdiction = roleService.findJurisdictionById(roleJurisdiction.getJurisdictionId());
				permissionNames.add(jurisdiction.getName());
				Jurisdiction parent = roleService.findJurisdictionById(jurisdiction.getParentId());
				permissionNames.add(parent.getName());
				roleNames.add(parent.getName());
			}
		}
        authorizationInfo.setRoles(roleNames);
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userService.findByUsername(userName);
        if (user == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
     /*  if (user.getLocked() == 0) {
            // 用户被管理员锁定抛出异常
            throw new LockedAccountException();
        }*/
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),
                user.getPassword(), getName());
        return authenticationInfo;
    }
}
