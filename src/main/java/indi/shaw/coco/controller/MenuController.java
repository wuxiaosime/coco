package indi.shaw.coco.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import indi.shaw.coco.access.AccessToken;
import indi.shaw.coco.model.Plan;
import indi.shaw.coco.service.PlanService;

@Controller
@RequestMapping("")
public class MenuController extends BaseController {
	private String baseUrl = "https://api.weixin.qq.com/cgi-bin/menu/";
	ObjectMapper mapper = new ObjectMapper();
	@Resource(name = "planService")
	private PlanService planService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public final String index(HttpServletResponse response) {
		System.out.println("turn to index");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setHeader("Access-Control-Allow-Headers", "Access-Control");
		response.setHeader("Allow", "POST,GET");
		return "index";
	}

	@RequestMapping(value = "savePlan", method = RequestMethod.POST)
	@ResponseBody
	public final void savePlan(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
		String reqdata = null;
		String resdata = null;
		String kcmc = null;
		String fmtDate = null;
		JSONObject redata = new JSONObject();
		String usernm = null;
		String scheduleList = null;
		Plan newPlan = null;
		String s = null;
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			reqdata = request.getParameter("data");
			System.out.println("----------保存计划(request):" + reqdata);
			JSONObject data = new JSONObject(reqdata);
			newPlan = mapper.readValue(reqdata, Plan.class);
			newPlan = planService.savePlan(newPlan);
			if (data.has("userid"))
				usernm = data.getString("userid");
			// kcmc = data.getString("value");// 课程名称
			/*
			 * if (StringUtils.isEmpty(kcmc)) { redata.put("data", "requery");
			 * redata.put("recode", "2"); writeJson(response, redata.toString()); return; }
			 */
			System.out.println(data);
			redata.put("data", new JSONObject(mapper.writeValueAsString(newPlan)));
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
		System.out.println("----------课程查询(response):" + redata.toString());
		writeJson(response, redata.toString());
	}

	@RequestMapping(value = "getPlanByDate", method = RequestMethod.POST)
	@ResponseBody
	public final void getPlanByDate(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
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
			while(iter.hasNext()) {
				Plan plan =Plans.get(iter.next());
				JSONObject planJSON = new JSONObject(mapper.writeValueAsString(plan));
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

	@RequestMapping(value = "deletePlan", method = RequestMethod.POST)
	@ResponseBody
	public final void deletePlan(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
		String reqdata = null;
		JSONObject redata = new JSONObject();
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			reqdata = request.getParameter("data");
			System.out.println("----------要删除的计划(request):" + reqdata);
			JSONObject data = new JSONObject(reqdata);
			planService.deletePlan(data.getLong("id"));
			redata.put("data", "id:"+data.getLong("id")+"的计划列表删除成功");
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
		System.out.println("----------删除计划(response):" + redata.toString());
		writeJson(response, redata.toString());
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void get(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
		String result = "{\"button\":[{\"type\":\"view\",\"name\":\"点林哥\",\"key\":\"test\", \"url\":\"\"}]}";
		try {
			response.getWriter().append(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void create() {
		HttpPost post = new HttpPost(baseUrl + "create?" + AccessToken.getAccess_token());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response;
		try {
			HttpEntity entity = new StringEntity("{\"button\":[{\"type\":\"view\",\"name\":\"点林哥\",\"key\":\"test\", \"url\":\"\"}]}");
			post.setEntity(entity);
			response = client.execute(post);
			InputStream is = response.getEntity().getContent();
			String text = null;
			InputStream input = is;

			byte[] bytes = new byte[1024];
			int nRead = 1;
			int nTotalRead = 0;
			while (nRead > 0) {
				nRead = input.read(bytes, nTotalRead, bytes.length - nTotalRead);
				if (nRead > 0)
					nTotalRead = nTotalRead + nRead;
			}
			text = new String(bytes, 0, nTotalRead, "utf-8");
			System.out.println("服务器端响应的数据：" + text);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
