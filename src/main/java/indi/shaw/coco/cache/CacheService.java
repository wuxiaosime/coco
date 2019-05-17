/**
 * @Package: indi.shaw.coco.cache
 * @author: shaw
 * @date: 2019年4月18日 下午4:05:23
 */
package indi.shaw.coco.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import indi.shaw.coco.util.LocateInfo;

/**
 * @author shaw
 *
 */
@Component("cacheService")
public class CacheService {
	@CachePut(value = "locate_Info", key = "#p0") // Cache是发生在cache1上的
	public LocateInfo putLocateInfo(Long userid, LocateInfo orgin) {
		return orgin;
	}

	@Cacheable(value = "locate_Info", key = "#p0") // Cache是发生在cache1上的
	public LocateInfo getLocateInfo(Long userid, LocateInfo orgin) {
		return orgin;
	}
}
