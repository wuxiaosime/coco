/**
 * @Package: indi.shaw.coco.dao
 * @author: shaw
 * @date: 2019年5月7日 上午2:02:33
 */
package indi.shaw.coco.dao;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import indi.shaw.coco.model.Customer;

/**
 * @author shaw
 *
 */
@Component("customerMapper")
public interface CustomerDao<PK extends Serializable> extends BaseDao<Customer, PK> {

}
