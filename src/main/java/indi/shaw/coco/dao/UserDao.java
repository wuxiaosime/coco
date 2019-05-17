/**
 * @Package: indi.shaw.coco.dao
 * @author: shaw
 * @date: 2018年7月30日 下午2:35:34
 */
package indi.shaw.coco.dao;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import indi.shaw.coco.model.User;

/**
 * @author shaw
 *
 */
@Component("userMapper")
public interface UserDao<PK extends Serializable> extends BaseDao<User, PK> {

	/**
	 * @param username
	 * @param username2
	 * @return
	 */
	User findByUsernameOrTelephone(String username, String username2);
}
