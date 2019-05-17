/**
 * @Package: indi.shaw.coco.dao.impl
 * @author: shaw
 * @date: 2019年5月7日 上午2:03:29
 */
package indi.shaw.coco.dao.impl;

import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.CustomerDao;
import indi.shaw.coco.model.Customer;

/**
 * @author shaw
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao<Long> {

}
