/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2019年4月22日 上午12:39:14
 */
package indi.shaw.coco.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw
 *
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	public User() {
		super();
	}

	public User(String name, String password) {
		super();
		if ("" != name)
			this.setName(name);
		if ("" != password)
			this.setPassword(password);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> role;

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
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *          the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public final Set<Role> getRole() {
		return role;
	}

	/**
	 * @param role
	 *          the role to set
	 */
	public final void setRole(Set<Role> role) {
		this.role = role;
	}

}
