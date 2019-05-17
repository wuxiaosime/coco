/**
 * @Package: indi.shaw.coco.dao
 * @author: shaw
 * @date: 2018年7月30日 下午2:35:34
 */
package indi.shaw.coco.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import indi.shaw.coco.model.Orders;
import indi.shaw.coco.model.Plan;

/**
 * @author shaw
 *
 */
@Component("ordersMapper")
public interface OrdersDao<PK extends Serializable> extends BaseDao<Orders, PK> {
	List<Long> updateOrders(Plan plan);
}
