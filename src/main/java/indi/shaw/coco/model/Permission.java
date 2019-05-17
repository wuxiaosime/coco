/**
 * @Package: indi.shaw.coco.model
 * @author: shaw
 * @date: 2019年4月23日 上午3:32:37
 */
package indi.shaw.coco.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw
 * @since 2019年4月23日上午3:32:55
 */
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "C_NAME")
	private String name; // 权限名称
	@Column(name = "C_KEYWORD")
	private String keyword; // 权限关键字，用于权限控制
	@Column(name = "C_DESCRIPTION")
	private String description; // 描述

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<Role>(0);

	/**
	 * @return
	 */
	public String getKeyword() {
		// TODO Auto-generated method stub
		return null;
	}
}
