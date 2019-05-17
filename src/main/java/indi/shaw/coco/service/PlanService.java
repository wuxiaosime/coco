/**
 * @Package: indi.shaw.coco.service
 * @author: shaw
 * @date: 2018年7月31日 上午2:20:48
 */
package indi.shaw.coco.service;

import java.util.Map;

import indi.shaw.coco.model.Plan;

/**
 * 
 * @author shaw
 *
 */
public interface PlanService extends BaseService {
	Plan savePlan(Plan plan);
	
	Plan getPlan(Long id);
	
	Map<String, Plan> getPlan(String date);

	/**
	 * @since 2019-04-14
	 * @param id 要删除的计划ID参数
	 */
	void deletePlan(Long...id);
}
