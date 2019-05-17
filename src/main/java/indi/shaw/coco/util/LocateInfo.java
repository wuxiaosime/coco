/**
 * @Package: indi.shaw.coco.util
 * @author: shaw
 * @date: 2019年4月17日 下午6:18:34
 */
package indi.shaw.coco.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shaw
 *
 */
public class LocateInfo {
	@JsonProperty(value = "lng")
	private Double lng;
	@JsonProperty(value = "lat")
	private Double lat;

	public LocateInfo() {

	}

	public LocateInfo(double lng, double lat) {
		this.lng = lng;
		this.lat = lat;
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
