/**
 * @Package: indi.shaw.coco.dao
 * @author: shaw
 * @date: 2019年4月8日 下午11:24:56
 */
package indi.shaw.coco.dao;

import java.io.Serializable;

import indi.shaw.coco.model.Address;

/**
 * @author shaw
 *
 */
public interface AddressDao<PK extends Serializable> extends BaseDao<Address, PK> {
}
