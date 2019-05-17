package indi.shaw.coco.material;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/material")
public class MaterialController {

	public void add(@RequestBody String requestBody, HttpServletResponse response) {
		String result = "";
		try {
			response.getWriter().append(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void get(@RequestBody String requestBody, HttpServletRequest request,
			HttpServletResponse response) {
		String url = request.getContextPath();
		String echostr = request.getParameter("echostr");
		int mediaId = 0;
		String result = "{\"media_id\":" + mediaId + "}";
		try {
			response.getWriter().append(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
