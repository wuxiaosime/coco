package indi.shaw.coco.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

public class StreamAccessor {
	private static final int buffer = 2 << 8;

	public static String Stream2String(InputStream is, int buffer) {
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

	public static String Stream2String(InputStream is) {
		return Stream2String(is, buffer);
	}

	public static void writeJson(HttpServletResponse response, String str) {
		try {
			response.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
