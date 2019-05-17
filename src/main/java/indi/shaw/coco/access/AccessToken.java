package indi.shaw.coco.access;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;

import indi.shaw.coco.util.StreamAccessor;

public class AccessToken {
	private static String APPID = "wxa713914246f75200";
	private static String APPSECRET = "f6df231d1376aecbabf7daa4036d215b";
	private static String access_token = "";

	public final static String getAccess_token() {
		if (access_token == "")
			token();
		return access_token;
	}

	@Scheduled(cron = "0 0 0/2 * * ? ")
	public final static void token() {
		String path = "https://api.weixin.qq.com/cgi-bin/token?"
				+ "grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
		HttpGet get = new HttpGet(path);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response;
		try {
			response = client.execute(get);
			InputStream is = response.getEntity().getContent();
			String text = StreamAccessor.Stream2String(is);
			JSONObject res = new JSONObject(text);
			access_token = res.getString("access_token");
			System.out.println("服务器端响应的数据：" + text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
