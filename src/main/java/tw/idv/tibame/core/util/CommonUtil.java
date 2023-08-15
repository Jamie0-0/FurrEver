<<<<<<< HEAD
package core.util;
=======
package tw.idv.tibame.core.util;

import static tw.idv.tibame.core.util.Constants.GSON;
import static tw.idv.tibame.core.util.Constants.JSON_MIME_TYPE;
>>>>>>> TonyYen

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import static core.util.Constants.GSON;
import static core.util.Constants.JSON_MIME_TYPE;
=======
>>>>>>> TonyYen

public class CommonUtil {
	public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
		response.setContentType(JSON_MIME_TYPE);
<<<<<<< HEAD
//		response.setCharacterEncoding("UTF-8");
=======
>>>>>>> TonyYen
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
