/**
 * @Package: indi.shaw.coco.service.impl
 * @author: shaw
 * @date: 2019年4月13日 上午3:34:04
 */
package indi.shaw.coco.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import indi.shaw.coco.service.SmsService;

/**
 * @author shaw
 *
 */
@Transactional
@Service("smsService")
public class smsServiceImpl extends BaseServiceImpl implements SmsService {

	// 短信应用SDK AppID
	int appid = 1400200092; // 1400开头

	// 短信应用SDK AppKey
	String appkey = "6b8ded594e303cbb690fcff37f4cfc9b";
	
	/**
	 * @author shaw
	 * @param smsSign 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请。
	 * @param phoneNumber 需要发送短信的手机号码
	 * @param params 数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
	 */
	@Override
	public JSONObject sendSms2Customer(String smsSign, String phoneNumber, ArrayList<String> params) {
		// 短信模板ID，需要在短信应用中申请
		int templateId = 7839; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
		// templateId7839对应的内容是"您的验证码是: {1}"
		try {
			SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
			SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, params, smsSign, "", ""); // 签名参数未提供或者为空时，会使用默认签名发送短信
			System.out.println(result);
		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
		}
		return null;
	}

}
