package tw.idv.tibame.product_fe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import tw.idv.tibame.product_fe.service.ProductService;

@WebServlet("/shop")
public class ShopController extends HttpServlet {
	@Autowired
	private ProductService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		var list = service.findAllForShopList();

		String message = gson.toJson("");
		message = "{\"status\":\"true\",\"findAllForShopList\":" + gson.toJson(list) + "}";
		resp.getWriter().write(message);

		System.out.println(message);

	}

}
