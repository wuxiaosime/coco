/**
 * @Package: indi.shaw.coco.service
 * @author: shaw
 * @date: 2018年5月30日 上午12:27:00
 */
package indi.shaw.coco.service;

import indi.shaw.coco.model.Orders;

/**
 * @author shaw
 *
 */
public interface OrdersService extends BaseService {
	Orders find(String date);

	Orders create();

	void update(Orders orders);

	void delete();
}
