/**
 * @Package: indi.shaw.coco.util
 * @author: shaw
 * @date: 2019年4月8日 下午11:19:23
 */
package indi.shaw.coco.util;

/**
 * @author shaw
 *
 */
public class StringUtils {
	public static boolean isEmpty(String str) {
		if (str == null || str.equals(""))
			return true;
		return false;
	}
}
