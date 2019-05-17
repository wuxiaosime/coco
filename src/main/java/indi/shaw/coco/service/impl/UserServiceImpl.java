/**
 * @Package: indi.shaw.coco.service.impl
 * @author: shaw
 * @date: 2019年4月24日 下午4:19:54
 */
package indi.shaw.coco.service.impl;

import java.util.List;

import javax.annotation.Resource;

import indi.shaw.coco.dao.UserDao;
import indi.shaw.coco.model.User;
import indi.shaw.coco.service.UserService;

/**
 * @author shaw
 *
 */
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	UserDao<Long> userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see indi.shaw.coco.service.UserService#login(indi.shaw.coco.model.User)
	 */
	@Override
	public boolean login(User user) {
		List<User> qList = userDao.findByExample(user);
		if(qList.size() > 0)
			return true;
		return false;
	}

}
