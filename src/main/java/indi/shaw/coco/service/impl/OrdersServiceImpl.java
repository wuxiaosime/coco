/**
 * @Package: indi.shaw.coco.service.impl
 * @author: shaw
 * @date: 2018年5月30日 上午12:36:14
 */
package indi.shaw.coco.service.impl;

import org.springframework.stereotype.Service;

import indi.shaw.coco.model.Orders;
import indi.shaw.coco.service.OrdersService;

/**
 * @author shaw
 *
 */
@Service("orderService")
public class OrdersServiceImpl implements OrdersService {

	/* (non-Javadoc)
	 * @see indi.shaw.coco.service.OrderService#find(java.lang.String)
	 */
	@Override
	public Orders find(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see indi.shaw.coco.service.OrderService#create()
	 */
	@Override
	public Orders create() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see indi.shaw.coco.service.OrderService#update(indi.shaw.coco.model.Orders)
	 */
	@Override
	public void update(Orders orders) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see indi.shaw.coco.service.OrderService#delete()
	 */
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
