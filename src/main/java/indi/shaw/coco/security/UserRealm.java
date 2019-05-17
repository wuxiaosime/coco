/**
 * @Package: indi.shaw.coco.security
 * @author: shaw
 * @date: 2019年4月29日 上午3:23:26
 */
package indi.shaw.coco.security;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import indi.shaw.coco.dao.PermissionDao;
import indi.shaw.coco.dao.UserDao;
import indi.shaw.coco.model.Permission;
import indi.shaw.coco.model.User;

/**
 * @author shaw
 *
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PermissionDao permissionDao;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// !这里的username可能并不是真正的username, 可能是手机号或者其他可以作为登录凭证的字段!
		String username = token.getUsername();

		User u = userDao.findByUsernameOrTelephone(username, username);
		/*if (u == null) {
			return null;
		}*/
		AuthenticationInfo info = new SimpleAuthenticationInfo(u, u.getPassword(), this.getName());
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		// 获取当前用户
		User user = (User) principalCollection.getPrimaryPrincipal();
		// 内置用户:授予所有权限
		List<Permission> permissions = null;
		if (user != null && user.getName().equals("admin")) {
			permissions = permissionDao.findAll();
		} else { // 其他普通用户:查出该用户对应的所有权限
			permissions = permissionDao.findByUserId(user.getId());
		}

		// 授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Permission p : permissions) {
			info.addStringPermission(p.getKeyword());
		}

		return info;
	}

}
