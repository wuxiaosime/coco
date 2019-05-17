/**
 * @Package: indi.shaw.coco.bo
 * @author: shaw
 * @date: 2018年8月5日 上午12:27:27
 */
package indi.shaw.coco.bo;

import java.io.Serializable;

import org.json.JSONArray;

/**
 * @author shaw
 *
 */
@SuppressWarnings("serial")
public class PlanBo implements Serializable {
	private String planId;
	private String expeDay;
	private JSONArray orderList;

	/**
	 * @return the planId
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param planId
	 *          the planId to set
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	/**
	 * @return the expeDay
	 */
	public String getExpeDay() {
		return expeDay;
	}

	/**
	 * @param expeDay
	 *          the expeDay to set
	 */
	public void setExpeDay(String expeDay) {
		this.expeDay = expeDay;
	}

	/**
	 * @return the orderList
	 */
	public JSONArray getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList
	 *          the orderList to set
	 */
	public void setOrderList(JSONArray orderList) {
		this.orderList = orderList;
	}

}
