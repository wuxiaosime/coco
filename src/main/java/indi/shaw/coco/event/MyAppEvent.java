/**
 * @Package: indi.shaw.coco.event
 * @author: shaw
 * @date: 2019年4月7日 上午12:03:41
 */
package indi.shaw.coco.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author shaw
 *
 */
public class MyAppEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param source
	 */
	public MyAppEvent(Object source) {
		super(source);
	}
}
