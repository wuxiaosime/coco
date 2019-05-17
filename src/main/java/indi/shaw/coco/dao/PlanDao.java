/**
 * @Package: indi.shaw.coco.dao
 * @author: shaw
 * @date: 2018年7月30日 下午2:35:34
 */
package indi.shaw.coco.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import indi.shaw.coco.model.Plan;

/**
 * @author shaw
 *
 */
@Component("planMapper")
public interface PlanDao<PK extends Serializable> extends BaseDao<Plan, PK> {
	List<Plan> getPlan(String date);

	Long newPlan(Plan plan);
}
