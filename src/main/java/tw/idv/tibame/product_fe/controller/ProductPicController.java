package tw.idv.tibame.product_fe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.tibame.product_fe.dao.ProductDao;
import tw.idv.tibame.product_fe.service.ProductService;

@WebServlet("/productPic")
public class ProductPicController extends HttpServlet {
	@Autowired
	private ProductService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int p_id = Integer.parseInt(req.getParameter("p_id"));
		byte[] pic_content = service.getPicByPid(p_id);

		resp.setContentType("image/gif, image/jpeg, image/png");
		ServletOutputStream out = resp.getOutputStream();
		out.write(pic_content);
		out.flush();
		out.close();
	}

}
