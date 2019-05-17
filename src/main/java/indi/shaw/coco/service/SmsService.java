/**
 * @Package: indi.shaw.coco.service
 * @author: shaw
 * @date: 2019年4月13日 上午3:31:26
 */
package indi.shaw.coco.service;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @author shaw
 *
 */
public interface SmsService extends BaseService {
	JSONObject sendSms2Customer(String smsSign, String phoneNumber, ArrayList<String> params);
}
