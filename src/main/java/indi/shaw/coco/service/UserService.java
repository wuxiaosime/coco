/**
 * @Package: indi.shaw.coco.service
 * @author: shaw
 * @date: 2019年4月24日 下午4:19:28
 */
package indi.shaw.coco.service;

import indi.shaw.coco.model.User;

/**
 * @author shaw
 *
 */
public interface UserService extends BaseService {
	boolean login(User user);
}
