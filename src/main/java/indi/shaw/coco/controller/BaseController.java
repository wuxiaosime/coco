package indi.shaw.coco.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/base")
public abstract class BaseController {
	final Logger logger = LoggerFactory.getLogger(BaseController.class);

	private final int buffer = 1024;
	
	protected String Stream2String(InputStream is, int buffer) {
		String str = "";
		byte[] bytes = new byte[buffer];
		int nRead = 1;
		int nTotalRead = 0;
		try {
			while (nRead > 0) {
				nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
				if (nRead > 0)
					nTotalRead = nTotalRead + nRead;
			}
			str = new String(bytes, 0, nTotalRead, "utf-8");
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
		return str;
	}
	
	protected String Stream2String(InputStream is) {
		return  Stream2String(is, this.buffer);
	}

	protected void writeJson(HttpServletResponse response, String str) {
		try {
			response.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}