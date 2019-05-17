/**
 * @Package: indi.shaw.coco.menu
 * @author: shaw
 * @date: 2019年4月10日 下午5:26:46
 */
package indi.shaw.coco.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import indi.shaw.coco.cache.CacheService;
import indi.shaw.coco.model.Plan;
import indi.shaw.coco.service.PlanService;
import indi.shaw.coco.service.SmsService;
import indi.shaw.coco.util.GCJ02_BD09;
import indi.shaw.coco.util.LocateInfo;
import indi.shaw.coco.util.StringUtils;

/**
 * @author shaw 微信预约小程序Controller
 */
@Controller
@RequestMapping("/wx")
public class WXAppoController extends BaseController {
	ObjectMapper mapper = new ObjectMapper();
	@Resource(name = "planService")
	private PlanService planService;
	@Resource(name = "smsService")
	private SmsService smsService;
	@Resource(name = "cacheService")
	private CacheService cacheService;
	@Value("#{wxProperties['weixin.appid']}")
	private String weixin_appid;
	@Value("#{wxProperties['weixin.secret']}")
	private String weixin_secret;
	@Value("#{wxProperties['weixin.jscode2session']}")
	private String wx_code2sess_url;
	private final String grant_type = "authorization_code";

	@RequestMapping(value = "onLogin", method = RequestMethod.GET)
	@ResponseBody
	public final void onLogin(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String reqdata = null;
		JSONObject redata = new JSONObject();
		try {
			boolean check = (StringUtils.isEmpty(code)) ? true : false;
			if (check) {
				throw new Exception("参数异常");
			}
			StringBuilder urlPath = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session"); // 微信提供的API，这里最好也放在配置文件
			urlPath.append(String.format("?appid=%s", weixin_appid));
			urlPath.append(String.format("&secret=%s", weixin_secret));
			urlPath.append(String.format("&js_code=%s", code));
			urlPath.append(String.format("&grant_type=%s", grant_type)); // 固定值
			HttpsURLConnection conn;
			URL url = new URL(urlPath.toString());
			InputStream is = null;
			ByteArrayOutputStream baos = null;
			conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(3000);
			conn.setConnectTimeout(3000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];

				while ((len = is.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				baos.flush();
				JSONObject resCode = new JSONObject(baos.toString());
				redata.put("data", resCode);
				System.out.println("微信服务器返回结果：" + resCode);
			} else {
				throw new RuntimeException(" responseCode is not 200 ... ");
			}
		} catch (Exception e) {
			try {
				redata.put("recode", "2");
			} catch (JSONException e1) {
				System.err.println(e1.getMessage());
			} finally {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
			e.printStackTrace();
		}
		writeJson(response, redata.toString());
	}

	@RequestMapping(value = "getPlanByDate", method = RequestMethod.GET)
	@ResponseBody
	public final void getPlanByDate(HttpServletRequest request, HttpServletResponse response) {
		String reqdata = null;
		JSONObject redata = new JSONObject();
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			reqdata = request.getParameter("data");
			System.out.println("----------获得计划(request):" + reqdata);
			JSONObject data = new JSONObject(reqdata);
			Map<String, Plan> Plans = planService.getPlan(data.getString("planDate"));
			Iterator<String> iter = Plans.keySet().iterator();
			JSONObject plans = new JSONObject();
			while (iter.hasNext()) {
				Plan plan = Plans.get(iter.next());
				JSONObject planJSON = new JSONObject(mapper.writeValueAsString(plan));
				if (planJSON.has("scheduleList")) {
					JSONArray list = planJSON.getJSONArray("scheduleList");
					if (list.length() < 1)
						break;
					for (int i = 0; i < list.length(); i++) {
						JSONObject json_orders = list.getJSONObject(i);
						if (json_orders.has("address")) {
							JSONObject json_address = json_orders.getJSONObject("address");
							Double bd09_lng = json_address.getDouble("lng");
							Double bd09_lat = json_address.getDouble("lat");
							LocateInfo localInfo = GCJ02_BD09.bd09_To_Gcj02(bd09_lng, bd09_lat);
							json_address.put("lng", localInfo.getLng());
							json_address.put("lat", localInfo.getLat());
						}
					}
				}
				plans.put(plan.getOperatorName(), planJSON);
			}
			redata.put("data", plans);
			System.out.println(data);
			redata.put("recode", "1");
			System.out.println(redata);
		} catch (Exception e) {
			try {
				redata.put("recode", "2");
			} catch (JSONException e1) {
				System.err.println(e1.getMessage());
			} finally {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
		System.out.println("----------获得计划(response):" + redata.toString());
		writeJson(response, redata.toString());
	}

	@RequestMapping(value = "receiveUserLocate", method = RequestMethod.GET)
	@ResponseBody
	public final void receiveUserLocate(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
		String reqdata = null;
		JSONObject redata = new JSONObject();
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			reqdata = request.getParameter("data");
			System.out.println("----------接收定位(request):" + reqdata);
			JSONObject data = new JSONObject(reqdata);
			String locateInfo_Str = data.getString("location_gcj02");
			LocateInfo locateInfo = mapper.readValue(locateInfo_Str, LocateInfo.class);
			locateInfo = GCJ02_BD09.gcj02_To_Bd09(locateInfo.getLng(), locateInfo.getLat());
			LocateInfo reslocateInfo = cacheService.putLocateInfo(1L, locateInfo);
			System.out.println(reslocateInfo);
			redata.put("data", data);
			System.out.println(data);
			redata.put("recode", "1");
			System.out.println(redata);
		} catch (Exception e) {
			try {
				redata.put("recode", "2");
			} catch (JSONException e1) {
				System.err.println(e1.getMessage());
			} finally {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
		System.out.println("----------接收定位(response):" + redata.toString());
		writeJson(response, redata.toString());
	}

	@RequestMapping(value = "getUserLocate", method = RequestMethod.GET)
	@ResponseBody
	public final void getUserLocate(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
		String reqdata = null;
		JSONObject redata = new JSONObject();
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			reqdata = request.getParameter("data");
			System.out.println("----------获得用户定位(request):" + reqdata);
			LocateInfo locateInfo = cacheService.getLocateInfo(1L, null);
			JSONObject locateInfoJson = null;
			if (null != locateInfo)
				locateInfoJson = new JSONObject(mapper.writeValueAsString(locateInfo));
			redata.put("data", locateInfoJson);
			System.out.println(locateInfo);
			redata.put("recode", "1");
			System.out.println(redata);
		} catch (Exception e) {
			try {
				redata.put("recode", "2");
			} catch (JSONException e1) {
				System.err.println(e1.getMessage());
			} finally {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
		System.out.println("----------获得用户定位(response):" + redata.toString());
		writeJson(response, redata.toString());
	}

	@RequestMapping(value = "sendSms2Customer", method = RequestMethod.POST)
	@ResponseBody
	public final void sendSms2Customer(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
		String reqdata = null;
		JSONObject redata = new JSONObject();
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			reqdata = request.getParameter("data");
			System.out.println("----------获得计划(request):" + reqdata);
			JSONObject data = new JSONObject(reqdata);
			ArrayList<String> params = new ArrayList<String>();
			JSONObject resdata = smsService.sendSms2Customer("smsSign", "phoneNumber", params);
			redata.put("data", resdata);
			System.out.println(data);
			redata.put("recode", "1");
			System.out.println(redata);
		} catch (Exception e) {
			try {
				redata.put("recode", "2");
			} catch (JSONException e1) {
				System.err.println(e1.getMessage());
			} finally {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
		System.out.println("----------获得计划(response):" + redata.toString());
		writeJson(response, redata.toString());
	}
}
