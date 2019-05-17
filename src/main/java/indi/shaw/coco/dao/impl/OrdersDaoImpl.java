/**
 * @Package: indi.shaw.coco.dao.impl
 * @author: shaw
 * @date: 2018年7月31日 上午2:17:44
 */
package indi.shaw.coco.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.OrdersDao;
import indi.shaw.coco.model.Orders;
import indi.shaw.coco.model.Plan;

/**
 * @author shaw
 *
 */
@Repository("ordersDao")
public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDao<Long> {

	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/*
	 * (non-Javadoc)
	 * 
	 * @see indi.shaw.coco.dao.OrdersDao#saveList(java.util.List)
	 */
	@Override
	public List<Long> updateOrders(Plan plan) {
		List<Long> idList = new ArrayList<Long>();
		for (Orders orders : plan.getOrdersList()) {
			orders.setCreateTime(new Date());
			orders.setPlan(plan);
		}
		return idList;
	}
}
