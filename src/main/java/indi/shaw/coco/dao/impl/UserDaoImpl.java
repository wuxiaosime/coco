/**
 * @Package: indi.shaw.coco.dao.impl
 * @author: shaw
 * @date: 2018年7月31日 上午2:17:44
 */
package indi.shaw.coco.dao.impl;

import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.UserDao;
import indi.shaw.coco.model.User;

/**
 * @author shaw
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<Long> {

	/* (non-Javadoc)
	 * @see indi.shaw.coco.dao.UserDao#findByUsernameOrTelephone(java.lang.String, java.lang.String)
	 */
	@Override
	public User findByUsernameOrTelephone(String username, String username2) {
		User user = new User();
		user.setName(username);
		user.setPassword("123456");
		return user;
	}
}
