/**
 * @Package: indi.shaw.coco.dao.impl
 * @author: shaw
 * @date: 2018年7月31日 上午2:17:44
 */
package indi.shaw.coco.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.PlanDao;
import indi.shaw.coco.model.Orders;
import indi.shaw.coco.model.Plan;

/**
 * @author shaw
 *
 */
@Repository("planDao")
public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao<Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see indi.shaw.coco.dao.PlanDao#getPlan(java.util.Date)
	 */
	@Override
	public List<Plan> getPlan(String date) {
		List<Plan> planList = null;
		Criteria criteria = getSession().createCriteria(Plan.class).add(Restrictions.eq("planDate", date));
		planList = criteria.list();
		return planList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see indi.shaw.coco.dao.PlanDao#newPlan(indi.shaw.coco.model.Plan)
	 */
	@Override
	public Long newPlan(Plan plan) {
		for (Orders orders : plan.getOrdersList()) {
			orders.setCreateTime(new Date());
			orders.setPlan(plan);
		}
		return plan.getId();
	}

}
