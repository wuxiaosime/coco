/**
 * @Package: indi.shaw.coco.dao.impl
 * @author: shaw
 * @date: 2019年4月8日 下午11:25:41
 */
package indi.shaw.coco.dao.impl;

import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.AddressDao;
import indi.shaw.coco.model.Address;

/**
 * @author shaw
 *
 */
@Repository("addressDao")
public class AddressDaoImpl extends BaseDaoImpl<Address> implements AddressDao<Long> {
}
