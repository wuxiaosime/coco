/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2019年4月8日 下午6:15:22
 */
package indi.shaw.coco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw 地址
 *
 */
@Entity
@Table(name = "b_address")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Address() {
	}

	public Address(String name) {
		this.name = name;
	}

	@JsonProperty(value = "id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Customer customer;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "lng", scale = 9, precision = 6)
	@JsonProperty(value = "lng")
	private Double lng;

	@Column(name = "lat", scale = 8, precision = 6)
	@JsonProperty(value = "lat")
	private Double lat;

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
	 * @return the lng
	 */
	public final Double getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *          the lng to set
	 */
	public final void setLng(Double lng) {
		this.lng = lng;
	}

	/**
	 * @return the lat
	 */
	public final Double getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *          the lat to set
	 */
	public final void setLat(Double lat) {
		this.lat = lat;
	}
}
