/**
 * @Package: indi.shaw.coco.bo
 * @author: shaw
 * @date: 2018年8月5日 上午12:23:35
 */
package indi.shaw.coco.bo;

import java.io.Serializable;

/**
 * @author shaw
 *
 */
@SuppressWarnings("serial")
public class OrdersBo implements Serializable {
	private String no;
	private String title;
	private String name;
	private String telephone;
	private String adress;
	private String distance;
	private String duration;
	private String endPointLng;
	private String endPointLat;
	private String type;
	private String status;
	private String itemList;
	private String className;

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no
	 *          the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *          the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress
	 *          the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *          the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *          the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the endPointLng
	 */
	public String getEndPointLng() {
		return endPointLng;
	}

	/**
	 * @param endPointLng
	 *          the endPointLng to set
	 */
	public void setEndPointLng(String endPointLng) {
		this.endPointLng = endPointLng;
	}

	/**
	 * @return the endPointLat
	 */
	public String getEndPointLat() {
		return endPointLat;
	}

	/**
	 * @param endPointLat
	 *          the endPointLat to set
	 */
	public void setEndPointLat(String endPointLat) {
		this.endPointLat = endPointLat;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *          the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *          the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the itemList
	 */
	public String getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
	 *          the itemList to set
	 */
	public void setItemList(String itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *          the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

}
