/**
 * @Package: indi.shaw.coco.service.impl
 * @author: shaw
 * @date: 2018年7月31日 上午2:24:37
 */
package indi.shaw.coco.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import indi.shaw.coco.dao.AddressDao;
import indi.shaw.coco.dao.CustomerDao;
import indi.shaw.coco.dao.OrdersDao;
import indi.shaw.coco.dao.PlanDao;
import indi.shaw.coco.model.Address;
import indi.shaw.coco.model.Customer;
import indi.shaw.coco.model.Orders;
import indi.shaw.coco.model.Plan;
import indi.shaw.coco.service.PlanService;
import indi.shaw.coco.util.StringUtils;

/**
 * @author shaw
 *
 */
@Transactional
@Service("planService")
public class PlanServiceImpl extends BaseServiceImpl implements PlanService {

	@Resource(name = "planDao")
	PlanDao<Long> planDao;

	@Resource(name = "ordersDao")
	OrdersDao<Long> ordersDao;

	@Resource(name = "customerDao")
	CustomerDao<Long> customerDao;

	@Resource(name = "addressDao")
	AddressDao<Long> addressDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see indi.shaw.coco.service.PlanService#getPlan(java.lang.Long)
	 */
	@Override
	public Plan getPlan(Long id) {
		Plan plan = planDao.findById(id);
		return plan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see indi.shaw.coco.service.PlanService#getPlan(java.util.Date)
	 */
	@Override
	public Map<String, Plan> getPlan(String date) {
		// TODO Auto-generated method stub
		Map<String, Plan> m = new HashMap<String, Plan>();
		for (Plan p : planDao.getPlan(date)) {
			m.put(p.getOperatorName(), p);
			p.getOrdersList().size();
			for (Orders order : p.getOrdersList()) {
				order.getCustomer();
				order.getContacts().size();
			}
		}
		return m;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see indi.shaw.coco.service.PlanService#savePlan(indi.shaw.coco.model.Plan)
	 */
	@Override
	public Plan savePlan(Plan plan) {
		Date createDate = new Date();
		plan.setCreateTime(createDate);
		/*
		 * if (plan.getId() == null) planDao.create(plan); else { planDao.update(plan);
		 * }
		 */
		for (Orders ordersNew : plan.getOrdersList()) {
			ordersNew.setCreateTime(createDate);
			ordersNew.setPlan(plan);
			Customer customerNew = ordersNew.getCustomer();
			if (customerNew != null && !StringUtils.isEmpty(customerNew.getPhoneNumber())) {
				if (customerNew.getId() == null) {
					customerDao.create(customerNew);
					Address addressNew = customerNew.getAddress();
					if (addressNew != null && !StringUtils.isEmpty(addressNew.getName())) {
						addressDao.create(addressNew);
					} else {
						addressDao.update(addressNew);
					}
				} else {
					customerDao.update(customerNew);
				}
				if (ordersNew.getId() == null)
					ordersDao.create(ordersNew);
				else
					ordersDao.update(ordersNew);
			}
			if (plan.getId() == null)
				planDao.create(plan);
			else
				planDao.update(plan);
		}
		return plan;
	}

	@Override
	public void deletePlan(Long... id) {
		for (int i = 0; i < id.length; i++) {
			Plan p = planDao.findById(id[i]);
			planDao.delete(p);
		}
	}
}
