package tw.idv.tibame.weimaster.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.tibame.weimaster.model.WeiMasterService;
import tw.idv.tibame.weimaster.model.MasterVO;
import tw.idv.tibame.weimaster.model.MasterVO2;

@WebServlet("/mas.do")
public class WeiMasterServlet extends HttpServlet {

	@Autowired
	private WeiMasterService service;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
//=============================================================================
//=============================================================================
//==================================遞出修改請求==================================
		System.out.println("action====="+action);
		if ("update".equals(action)) {
			
			//取得網頁傳來資資料
			Integer m_id = (Integer)session.getAttribute("mid");
			String m_name = String.valueOf(req.getParameter("m_name"));
			String m_gui = String.valueOf(req.getParameter("m_gui"));
			String m_bank_id = String.valueOf(req.getParameter("m_bank_id"));
			String m_address = String.valueOf(req.getParameter("m_address"));
			String m_man_id = String.valueOf(req.getParameter("m_man_id"));
			String m_man_name = String.valueOf(req.getParameter("m_man_name"));
			String m_email = String.valueOf(req.getParameter("m_email"));
			String m_phone = String.valueOf(req.getParameter("m_phone"));

			//更新資料庫
			service.updatePro(m_id,m_name
					,m_gui,m_bank_id,m_address
					,m_man_id,m_man_name,m_email,m_phone);
			//返還資料到網頁
			tw.idv.tibame.core.util.CommonUtil.writePojo2Json(res,
					tw.idv.tibame.core.util.Constants.GSON.toJson("Update successful"));
		}
//=============================================================================
//=============================================================================
//==================================頁面單一查詢==================================
		if ("getOneSearch".equals(action)) {
			
			//查詢資料
			Integer m_id = (Integer)session.getAttribute("mid");
			MasterVO mVO = service.getOnePro(m_id);

			//資料塞入masterVO2
			MasterVO2 masterVO2 = new MasterVO2.Builder()
												.setM_id(mVO.getM_id())
												.setM_name(mVO.getM_name())
												.setM_gui(mVO.getM_gui())
												.setM_bank_id(mVO.getM_bank_id())
												.setM_address(mVO.getM_address())
												.setM_man_id(mVO.getM_man_id())
												.setM_man_name(mVO.getM_man_name())
												.setM_email(mVO.getM_email())
												.setM_phone(mVO.getM_phone())
												.build();
			//返還資料到網頁
			tw.idv.tibame.core.util.CommonUtil.writePojo2Json(res,
					tw.idv.tibame.core.util.Constants.GSON.toJson(masterVO2));
		}
	}
}
