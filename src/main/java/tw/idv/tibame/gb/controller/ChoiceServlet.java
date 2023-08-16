package tw.idv.tibame.gb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import tw.idv.tibame.gb.dao.GBDao;
import tw.idv.tibame.gb.vo.GbAndProductVO;

@WebServlet("/choise")
public class ChoiceServlet extends HttpServlet {

	private static final long serialVersionUID = 5314977473749785269L;

	@Autowired
	private GBDao dao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json; charset=UTF-8");

		Gson gson = new Gson();

		String how = req.getParameter("how");
		String keywords = req.getParameter("keywords");

		System.out.println(how);
		System.out.println(keywords);

		List<GbAndProductVO> list = dao.selectByKeyWords(how, keywords);
		System.out.println(list);
		String message = gson.toJson("");

		message = "{\"status\":\"true\",\"selectByKeyWords\":" + gson.toJson(list) + "}";
		resp.getWriter().write(message);

		System.out.println(message);

	}
}
