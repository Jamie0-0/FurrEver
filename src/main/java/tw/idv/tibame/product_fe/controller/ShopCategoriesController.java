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

@WebServlet("/shop/categories")
public class ShopCategoriesController extends HttpServlet {

	@Autowired
	private ProductService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();

		String how = req.getParameter("how");
		String keywords = req.getParameter("keywords");

		var list = service.selectByKeyWords(how, keywords);

		String message = gson.toJson("");

		message = "{\"status\":\"true\",\"selectByKeyWords\":" + gson.toJson(list) + "}";
		resp.getWriter().write(message);

		System.out.println(message);

	}

//	@GetMapping("/shop/categories")
//	public Map<String, Object> selectByKeyWords(@RequestParam String how, @RequestParam String keywords) {
//		List<Product> list = service.selectByKeyWords(how, keywords);
//		Map<String, Object> response = new HashMap<>();
//		response.put("status", "true");
//		response.put("selectByKeyWords", list);
//
//		return response;
//	}

}
