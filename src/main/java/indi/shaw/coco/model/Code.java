/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2019年4月9日 上午1:41:04
 */
package indi.shaw.coco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw
 *
 */
@Entity
@Table(name = "b_code")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Code implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value="id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code_type", nullable = false, length = 40)
	private String type;

	@Column(name = "code_name", nullable = false, length = 40)
	private String name;

	@Column(name = "code_value", nullable = false, length = 40)
	private String value;

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
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * @param type
	 *          the type to set
	 */
	public final void setType(String type) {
		this.type = type;
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
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value
	 *          the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

}
