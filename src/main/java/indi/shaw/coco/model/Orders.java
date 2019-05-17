/**
 * @Package: indi.shaw.coco.bo
 * @author: shaw
 * @date: 2018年4月30日 下午12:03:52
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw 订单
 */
@Entity
@Table(name = "b_orders")
public final class Orders implements Serializable {
	private static final long serialVersionUID = 10000002L;

	/*
	 * @JsonCreator() public Orders(@JsonProperty("id")String id){ this.id = id; }
	 */

	@JsonCreator()
	public Orders() {
		super();
	}

	@JsonProperty(value = "id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "orders")
	private OrdersAppo ordersAppo;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "plan_id")
	@JsonIgnore
	private Plan plan;

	@OneToMany(mappedBy = "orders")
	@LazyCollection(LazyCollectionOption.TRUE)
	@JsonProperty(value = "contacts")
	private List<Contact> contacts;

	@Column(name = "name", nullable = false, length = 20)
	@JsonProperty(value = "name")
	private String name;

	@Column(name = "title")
	@JsonProperty(value = "title")
	private String title;

	@Column(name = "telephone", length = 20)
	@JsonProperty(value = "telephone")
	private String telephone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@JsonProperty(value = "customer")
	private Customer customer;

	/*
	 * @Column(name = "endPointLng")
	 * 
	 * @JsonProperty(value = "endPointLng") private Float endPointLng = 0f;
	 * 
	 * @Column(name = "endPointLat")
	 * 
	 * @JsonProperty(value = "endPointLat") private Float endPointLat = 0f;
	 */

	@Column(name = "status")
	@JsonProperty(value = "status")
	private String status;

	@Column(name = "no")
	@JsonProperty(value = "no")
	private Integer no;

	@Column(name = "createtime")
	@JsonProperty(value = "createtime")
	private Date createTime;

	@Column(name = "updatetime")
	@JsonProperty(value = "updatetime")
	private Date updateTime;

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id
	 *          the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ordersAppo
	 */
	public final OrdersAppo getOrdersAppo() {
		return ordersAppo;
	}

	/**
	 * @param ordersAppo
	 *          the ordersAppo to set
	 */
	public final void setOrdersAppo(OrdersAppo ordersAppo) {
		this.ordersAppo = ordersAppo;
	}

	/**
	 * @return the plan
	 */
	public final Plan getPlan() {
		return plan;
	}

	/**
	 * @param plan
	 *          the plan to set
	 */
	public final void setPlan(Plan plan) {
		this.plan = plan;
	}

	/**
	 * @return the contacts
	 */
	public final List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts
	 *          the contacts to set
	 */
	public final void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *          the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the telephone
	 */
	public final String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *          the telephone to set
	 */
	public final void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the customer
	 */
	public final Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *          the customer to set
	 */
	public final void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *          the status to set
	 */
	public final void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the no
	 */
	public final Integer getNo() {
		return no;
	}

	/**
	 * @param no
	 *          the no to set
	 */
	public final void setNo(Integer no) {
		this.no = no;
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

	/**
	 * @return the updateTime
	 */
	public final Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *          the updateTime to set
	 */
	public final void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
