/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2018年5月7日 上午12:57:34
 */
package indi.shaw.coco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author shaw
 *
 */
@Entity
@Table(name = "b_contact")
public class Contact implements Serializable {
	private static final long serialVersionUID = 20000002L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orders_id")
	@JsonIgnore
	private Orders orders;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "telephone")
	private String telephone;

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
	 * @return the orders
	 */
	public Orders getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *          the orders to set
	 */
	public void setOrders(Orders orders) {
		this.orders = orders;
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
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *          the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
