/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2018年6月5日 上午2:44:08
 */
package indi.shaw.coco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw 计划
 */
@Entity
@Table(name = "b_plan")
public class Plan implements Serializable {
	private static final long serialVersionUID = 10000003L;

	@JsonProperty(value = "id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	// @JsonProperty(value = "id")
	private String name;

	@JsonProperty(value = "operatorName")
	@Column(name="operrator_name", length=20)
	private String operatorName;

	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty(value = "scheduleList")
	private List<Orders> ordersList;

	@JsonProperty(value = "date")
	@Column(name = "p_date", nullable = false, length = 8)
	private String planDate;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the operatorName
	 */
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * @param operatorName
	 *          the operatorName to set
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * @return the ordersList
	 */
	public List<Orders> getOrdersList() {
		return ordersList;
	}

	/**
	 * @param ordersList
	 *          the ordersList to set
	 */
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return planDate;
	}

	/**
	 * @param date
	 *          the date to set
	 */
	public void setDate(String planDate) {
		this.planDate = planDate;
	}

	/**
	 * @return the createTime
	 */
	public final Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *          the createTime to set
	 */
	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
