package cn.ccenjie.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ccenjie.shiro.dao.UserDao;
import cn.ccenjie.shiro.entity.User;

@Component
public class DaoRealm extends AuthorizingRealm {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 角色和权限授权覆盖
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
		String role = userDao.findRoleByUsername(username);
		String perm = userDao.findPermByUsername(username);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		roles.add(role);
		info.setRoles(roles);
		Set<String> stringPermissions = new HashSet<String>();
		stringPermissions.add(perm);
		info.setStringPermissions(stringPermissions);
		return null;
	}
	
	/**
	 * 用户验证覆盖
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		
		String username = (String)token.getPrincipal();
		User user = userDao.findByUsername(username);
		AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "user");
		return info;
	}

}
