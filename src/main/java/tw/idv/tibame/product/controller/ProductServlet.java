package tw.idv.tibame.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.tibame.product.model.ProductService;
import tw.idv.tibame.product.model.ProductVO;
import tw.idv.tibame.tools.Tools;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 10 // 10MB
)


@WebServlet("/pro.do")
public class ProductServlet extends HttpServlet {
	@Autowired
	private ProductService service;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		System.out.println("action===="+action);

//=============================================================================
//=============================================================================
//=====================================單一查詢==================================
		if ("getOneSearch".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數 - 輸入格式的錯誤處理
			String str = req.getParameter("p_id");
			if (str == null || (str.trim()).length() == 0) {
				str = "0";
			}

			Integer p_id = Integer.valueOf(str);
			Integer p_status = Integer.valueOf(req.getParameter("p_status"));
			Integer p_class = Integer.valueOf(req.getParameter("p_class"));
			Integer mid = (Integer)session.getAttribute("mid");


			if (p_id == 0 && p_status == 0 && p_class == 0) {
				errorMsgs.add("全部查詢");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/products.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			// 開始查詢資料
			List productVO = service.getOnePro2(p_id, p_status, p_class,mid);

			if (productVO == null) {
				errorMsgs.add("查無資料，以全部查詢替代");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/products.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			// 查詢完成,準備轉交
			ArrayList<ProductVO> list = (ArrayList<ProductVO>) productVO;
			req.setAttribute("list", list);
			String url = "/backEnd/products.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//=============================================================================
//=============================================================================
//==================================進入修改頁面=================================
		if ("getUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數
			Integer p_id = Integer.valueOf(req.getParameter("p_id"));
			Integer mid = (Integer)session.getAttribute("mid");
			String p_status = req.getParameter("p_status");

			// 開始查詢資料
			ProductVO proVO = service.getOnePro(p_id,mid);

			// 將位元資料流轉換為 Base64 編碼
			String p1 = "";
			String p2 = "";
			String p3 = "";
			String p4 = "";

			if(proVO.getP_pic_one() != null) {
				p1 = Base64.getEncoder().encodeToString(proVO.getP_pic_one());
			}
			if(proVO.getP_pic_two() != null) {
				p2 = Base64.getEncoder().encodeToString(proVO.getP_pic_two());
			}
			if(proVO.getP_pic_three() != null) {
				p3 = Base64.getEncoder().encodeToString(proVO.getP_pic_three());
			}
			if(proVO.getP_pic_four() != null) {
				p4 = Base64.getEncoder().encodeToString(proVO.getP_pic_four());
			}

			// 將 Base64 編碼的圖片位元資料流傳遞給 JSP 頁面
			req.setAttribute("p1", p1);
			req.setAttribute("p2", p2);
			req.setAttribute("p3", p3);
			req.setAttribute("p4", p4);

			// 查詢完成,準備轉交
			req.setAttribute("proVO", proVO);
			String url = "/backEnd/update-product.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//=============================================================================
//=============================================================================
//==================================修改頁面遞出請求==============================
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer mid = (Integer)session.getAttribute("mid");

			// 商品編號一定正確故不檢查
			Integer p_id = Integer.valueOf(req.getParameter("p_id"));

			// 商品名稱 - 錯誤處理
			String p_name = req.getParameter("p_name");
			String errMsg = "";
			if (p_name == null || p_name.trim().length() == 0) {
				p_name = "";
				errorMsgs.add("商品名稱: 請勿空白");
			} else {
				errMsg = Tools.check(p_name, "word");
				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品名稱:" + errMsg);
				}
			}

			// 商品價格 - 錯誤處理
			String p_price = req.getParameter("p_price");
			errMsg = "";
			if (p_price == null || p_price.trim().length() == 0) {
				p_price = "0";
				errorMsgs.add("商品價格: 請勿空白");
			} else {
				errMsg = Tools.check(p_price, "num");

				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品價格:" + errMsg);
					p_price = "0";
				}
			}

			// 商品數量 - 錯誤處理
			String p_stock = req.getParameter("p_stock");
			errMsg = "";
			if (p_stock == null || p_stock.trim().length() == 0) {
				p_stock = "0";
				errorMsgs.add("商品數量: 請勿空白");
			} else {
				errMsg = Tools.check(p_stock, "num");
				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品數量:" + errMsg);
					p_stock = "0";
				}
			}

			Integer p_type = Integer.parseInt(req.getParameter("p_type"));
			Integer p_status = Integer.parseInt(req.getParameter("p_status"));
			Integer p_class = Integer.parseInt(req.getParameter("p_class"));
			String p_des = req.getParameter("p_des");

			Part filePart1 = req.getPart("p_pic_one");
			Part filePart2 = req.getPart("p_pic_two");
			Part filePart3 = req.getPart("p_pic_three");
			Part filePart4 = req.getPart("p_pic_four");

			InputStream filecontent1 = filePart1.getInputStream();
			byte[] p_pic_one = new byte[filecontent1.available()];
			filecontent1.read(p_pic_one);
			
			InputStream filecontent2 = filePart2.getInputStream();
			byte[] p_pic_two = new byte[filecontent2.available()];
			filecontent2.read(p_pic_two);

			InputStream filecontent3 = filePart3.getInputStream();
			byte[] p_pic_three = new byte[filecontent3.available()];
			filecontent3.read(p_pic_three);

			InputStream filecontent4 = filePart4.getInputStream();
			byte[] p_pic_four = new byte[filecontent4.available()];
			filecontent4.read(p_pic_four);

			//查詢資料庫是否有紀錄
			ProductVO productVO1 = service.getOnePro(p_id,mid);

			Integer p_idSearch = productVO1.getP_id();

			if(p_idSearch == null || p_idSearch == 0) {
				if(p_pic_one.length == 0) {
					errorMsgs.add("圖1:請輸入圖片");
				}
				
				if(p_pic_two.length == 0) {
					errorMsgs.add("圖2:請輸入圖片");
				}			
				
				if(p_pic_three.length == 0) {
					errorMsgs.add("圖3:請輸入圖片");
				}
				
				if(p_pic_four.length == 0) {
					errorMsgs.add("圖4:請輸入圖片");
				}
			}

			ProductVO proVO = new ProductVO.Builder()
											.setP_m_id(mid)
											.setP_id(p_id)
											.setP_name(p_name)
											.setP_price(Integer.parseInt(p_price))
											.setP_stock(Integer.parseInt(p_stock))
											.setP_type(p_type)
											.setP_status(p_status)
											.setP_class(p_class)
											.setP_des(p_des)
											.build();

			if (!errorMsgs.isEmpty()) {
				filecontent4.close();
				filecontent3.close();
				filecontent2.close();
				filecontent1.close();
				String p1 = "";
				String p2 = "";
				String p3 = "";
				String p4 = "";

				if(productVO1.getP_pic_one() != null) {
					p1 = Base64.getEncoder().encodeToString(productVO1.getP_pic_one());
				}
				if(productVO1.getP_pic_two() != null) {
					p2 = Base64.getEncoder().encodeToString(productVO1.getP_pic_two());
				}
				if(productVO1.getP_pic_three() != null) {
					p3 = Base64.getEncoder().encodeToString(productVO1.getP_pic_three());
				}
				if(productVO1.getP_pic_four() != null) {
					p4 = Base64.getEncoder().encodeToString(productVO1.getP_pic_four());
				}
				// 將 Base64 編碼的圖片位元資料流傳遞給 JSP 頁面
				req.setAttribute("p1", p1);
				req.setAttribute("p2", p2);
				req.setAttribute("p3", p3);
				req.setAttribute("p4", p4);

				req.setAttribute("proVO", proVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/update-product.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			//開始修改資料
			proVO = service.updatePro(mid,p_name, Integer.parseInt(p_price), Integer.parseInt(p_stock), p_type, p_class,
					p_des, p_status, p_id, p_pic_one, p_pic_two, p_pic_three, p_pic_four);

			//修改完成,準備轉交
			List productVO = service.getOnePro2(p_id, p_status, p_class,mid);

			ArrayList<ProductVO> list = (ArrayList<ProductVO>) productVO;

			// 查詢完成,準備轉交
			req.setAttribute("list", list);

			String url = "/backEnd/products.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			filecontent4.close();
			filecontent3.close();
			filecontent2.close();
			filecontent1.close();
		}

//=============================================================================
//=============================================================================
//=====================================新增資料==================================
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 商品名稱 - 錯誤處理
			String p_name = req.getParameter("p_name");
			String errMsg = "";
			if (p_name == null || p_name.trim().length() == 0) {
				p_name = "";
				errorMsgs.add("商品名稱: 請勿空白");
			} else {
				errMsg = Tools.check(p_name, "word");
				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品名稱:" + errMsg);
				}
			}

			// 商品價格 - 錯誤處理
			String p_price = req.getParameter("p_price");
			errMsg = "";
			if (p_price == null || p_price.trim().length() == 0) {
				p_price = "0";
				errorMsgs.add("商品價格: 請勿空白");
			} else {
				errMsg = Tools.check(p_price, "num");

				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品價格:" + errMsg);
					p_price = "0";
				}
			}

			// 商品數量 - 錯誤處理
			String p_stock = req.getParameter("p_stock");
			errMsg = "";
			if (p_stock == null || p_stock.trim().length() == 0) {
				p_stock = "0";
				errorMsgs.add("商品數量: 請勿空白");
			} else {
				errMsg = Tools.check(p_stock, "num");
				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品數量:" + errMsg);
					p_stock = "0";
				}
			}

			Integer p_m_id = (Integer)session.getAttribute("mid");
			Integer p_type = Integer.parseInt(req.getParameter("p_type"));
			Integer p_class = Integer.parseInt(req.getParameter("p_class"));
			String p_des = req.getParameter("p_des");

			Part filePart1 = req.getPart("p_pic_one");
			Part filePart2 = req.getPart("p_pic_two");
			Part filePart3 = req.getPart("p_pic_three");
			Part filePart4 = req.getPart("p_pic_four");

			InputStream filecontent1 = filePart1.getInputStream();
			byte[] p_pic_one = new byte[filecontent1.available()];
			filecontent1.read(p_pic_one);

			InputStream filecontent2 = filePart2.getInputStream();
			byte[] p_pic_two = new byte[filecontent2.available()];
			filecontent2.read(p_pic_two);

			InputStream filecontent3 = filePart3.getInputStream();
			byte[] p_pic_three = new byte[filecontent3.available()];
			filecontent3.read(p_pic_three);

			InputStream filecontent4 = filePart4.getInputStream();
			byte[] p_pic_four = new byte[filecontent4.available()];
			filecontent4.read(p_pic_four);
			
			if(p_pic_one.length == 0) {
				errorMsgs.add("圖片一請輸入圖片");
			}
			if(p_pic_two.length == 0) {
				errorMsgs.add("圖片二請輸入圖片");
			}
			if(p_pic_three.length == 0) {
				errorMsgs.add("圖片三請輸入圖片");
			}
			if(p_pic_four.length == 0) {
				errorMsgs.add("圖片四請輸入圖片");
			}
			
			ProductVO proVO = new ProductVO.Builder()
											.setP_name(p_name)
											.setP_price(Integer.parseInt(p_price))
											.setP_stock(Integer.parseInt(p_stock))
											.setP_type(p_type)
											.setP_class(p_class)
											.setP_des(p_des)
											.build();

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("proVO", proVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/add-new-product.jsp");
				failureView.forward(req, res);
				return;
			}

			//開始新增資料

//			ProductService proSvc = new ProductService();
			proVO = service.addPro(p_m_id,p_name,

					Integer.parseInt(p_price), Integer.parseInt(p_stock),p_type,
					p_class,p_des,
					p_pic_one,p_pic_two,p_pic_three,p_pic_four);

			//新增完成,查出新商品
			List productVO = service.searchLatest();

			ArrayList<ProductVO> list = (ArrayList<ProductVO>) productVO;
			req.setAttribute("list", list);

			//新增完成,準備轉交
			String url = "/backEnd/products.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//=============================================================================
//=============================================================================
//=====================================刪除資料==================================
		if ("delete".equals(action)) {
			Integer p_id = Integer.valueOf(req.getParameter("p_id"));
			Integer mid = (Integer)session.getAttribute("mid");

			// 開始刪除資料
			try {
				service.deletePro(p_id,mid);

			} catch (Exception e){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				errorMsgs.add("此筆資料已有消費紀錄，不能刪除");

				ArrayList<ProductVO> list = (ArrayList<ProductVO>) service.getAll();

				req.setAttribute("list", list);
				
				String url2 = "/backEnd/products.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url2);
				failureView.forward(req, res);
				return;
			}

			// 查詢完成,準備轉交
			String url = "/backEnd/products.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
