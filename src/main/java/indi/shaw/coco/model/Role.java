/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2019年4月22日 上午12:40:01
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
@Table(name = "t_role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 40)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_role_permssion", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "permmsion_id") })
	private Set<Permission> permissions;
}
