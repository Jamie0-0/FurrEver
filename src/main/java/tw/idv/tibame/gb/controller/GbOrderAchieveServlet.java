package tw.idv.tibame.gb.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import tw.idv.tibame.gb.service.GBService;

@WebServlet("/gborderachieve")
public class GbOrderAchieveServlet extends HttpServlet {

	private static final long serialVersionUID = 2431987099112833524L;

	@Autowired
	private GBService gbService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Gson gson = new Gson();

		BufferedReader reader = req.getReader();
		JsonObject requestData = gson.fromJson(reader, JsonObject.class);
		int gbId = requestData.get("gbId").getAsInt();

		// 根据 gb_id 进行后续处理，如数据库查询等
		int result = gbService.updateGbStatusToZero(gbId);

		// 构建回应数据，例如：
		JsonObject responseObject = new JsonObject();
		responseObject.addProperty("message", "Received gb_id: " + result);

		String message = gson.toJson(responseObject);
		resp.getWriter().write(message);
	}
}
