package indi.shaw.coco.event;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/wx")
public class EventController {

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void signature(@RequestBody String requestBody,HttpServletRequest request, HttpServletResponse response) {
		String echostr = request.getParameter("echostr");
		try {
			response.getWriter().append(echostr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
