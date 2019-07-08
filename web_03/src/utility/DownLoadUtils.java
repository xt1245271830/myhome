package utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

public class DownLoadUtils {
	public static String getName(String agent, String filename) throws UnsupportedEncodingException {
		if (agent.contains("Firefox")) {
			// 火狐浏览器
			Base64.Encoder encoder = Base64.getEncoder();
			filename = "=?utf-8?B?" + encoder.encodeToString(filename.getBytes("utf-8")) + "?=";
		} else {
			// 其它浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
