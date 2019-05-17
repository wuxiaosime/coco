/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2018年8月4日 下午7:43:23
 */
package indi.shaw.coco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author shaw 订单预约
 */
@Entity
@Table(name = "b_orders_appo")
public class OrdersAppo implements Serializable {
	private static final long serialVersionUID = 10000004L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "appo_id")
	private Orders orders;

	@Column(name = "distance")
	private Float distance;

	@Column(name = "duration")
	private Float duration;

	@Column(name = "expeDay")
	private Date expeDay;

	@Column(name = "createtime")
	private Date createTime;

	@Column(name = "updatetime")
	private Date updateTime;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *          the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the order
	 */
	public Orders getOrder() {
		return orders;
	}

	/**
	 * @param order
	 *          the order to set
	 */
	public void setOrder(Orders orders) {
		this.orders = orders;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *          the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	/**
	 * @return the duration
	 */
	public Float getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *          the duration to set
	 */
	public void setDuration(Float duration) {
		this.duration = duration;
	}

	/**
	 * @return the expeDay
	 */
	public Date getExpeDay() {
		return expeDay;
	}

	/**
	 * @param expeDay
	 *          the expeDay to set
	 */
	public void setExpeDay(Date expeDay) {
		this.expeDay = expeDay;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *          the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *          the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
