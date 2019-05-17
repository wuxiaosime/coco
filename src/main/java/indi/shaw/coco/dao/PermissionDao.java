/**
 * @Package: indi.shaw.coco.dao
 * @author: shaw
 * @date: 2019年4月29日 上午3:25:26
 */
package indi.shaw.coco.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import indi.shaw.coco.model.Permission;

/**
 * @author shaw
 *
 */
@Component("permissionMapper")
public interface PermissionDao<PK extends Serializable> extends BaseDao<Permission, PK> {

	/**
	 * @param id
	 * @return
	 */
	List<org.apache.shiro.authz.Permission> findByUserId(Long id);

}
