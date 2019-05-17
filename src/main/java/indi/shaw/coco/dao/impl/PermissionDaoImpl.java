/**
 * @Package: indi.shaw.coco.dao.impl
 * @author: shaw
 * @date: 2018年7月31日 上午2:17:44
 */
package indi.shaw.coco.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.PermissionDao;
import indi.shaw.coco.model.Permission;

/**
 * @author shaw
 *
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao<Long> {

	/* (non-Javadoc)
	 * @see indi.shaw.coco.dao.PermissionDao#findByUserId(java.lang.Long)
	 */
	@Override
	public List<org.apache.shiro.authz.Permission> findByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
