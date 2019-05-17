/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2018年5月7日 上午12:52:37
 */
package indi.shaw.coco.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw 客户
 */
@Entity
@Table(name = "b_customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 10000001L;

	@JsonProperty(value = "id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 20)
	private Long name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "phonenumber", length = 20)
	private String phoneNumber;

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
	 * @return the name
	 */
	public final Long getName() {
		return name;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public final void setName(Long name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public final Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *          the address to set
	 */
	public final void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public final String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *          the phoneNumber to set
	 */
	public final void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
