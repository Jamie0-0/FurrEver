package tw.idv.tibame.gb.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import tw.idv.tibame.gb.service.GBService;
import tw.idv.tibame.gb.vo.GbOrderVO;

@WebServlet("/JoinGbServlet")
public class JoinGbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private GBService service;

//	public JoinGbServlet() {
//		service = new GbServiceImpl();
//	}

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        Gson gson = new Gson();
//
//        List<GbOrderVO> gbOrders = new ArrayList<>();
//
//        try (Connection conn = GBDatabaseUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(
//                     "SELECT * FROM gb_order")) {
//
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                GbOrderVO gbOrder = new GbOrderVO();
//                gbOrder.setGb_id(rs.getInt("gb_id"));
//                gbOrder.setUid(rs.getInt("uid"));
//                gbOrder.setGb_order_id(rs.getInt("gb_order_id"));
//
//
//                gbOrders.add(gbOrder);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("status", "true");
//
//        JsonArray gbOrderArray = new JsonArray();
//        for (GbOrderVO gbOrder : gbOrders) {
//            JsonObject gbOrderObject = new JsonObject();
//            gbOrderObject.addProperty("gb_id", gbOrder.getGb_id());
//            gbOrderObject.addProperty("uid", gbOrder.getUid());
//            gbOrderObject.addProperty("gb_order_id", gbOrder.getGb_order_id());
//            // Set other fields in gbOrderObject
//
//            gbOrderArray.add(gbOrderObject);
//        }
//        jsonObject.add("gbOrders", gbOrderArray);
//
//        
//        
//        
//        String message = gson.toJson(jsonObject);
//        response.getWriter().write(message);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
//    
//    
//    
//    @Override
//    public void init() throws ServletException {
//        service = new GbServiceImpl();
//    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		BufferedReader reader = request.getReader();
		JsonObject requestData = gson.fromJson(reader, JsonObject.class);

		int gbPNum = requestData.get("gb_p_num").getAsInt();
		int uid = requestData.get("uid").getAsInt();
		int orderIid = requestData.get("order_id").getAsInt();
		String gbDate = requestData.get("gb_date").getAsString();
		String gbT = requestData.get("gb_t").getAsString();
		int gpT1 = Integer.parseInt(gbT);

		GbOrderVO gbOrder = new GbOrderVO();

		gbOrder.setGb_p_num(gbPNum);
		gbOrder.setUid(uid);
		gbOrder.setGb_order_id(orderIid);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parsedDate = dateFormat.parse(gbDate);
			// 此 parsedDate 即 java.util.Date 對象
			System.out.println("Parsed Date: " + parsedDate);
			gbOrder.setGb_date(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gbOrder.setGb_t(gpT1);

		boolean insertSuccess = service.insertGbOrder(gbOrder);

		JsonObject jsonObject = new JsonObject();
		if (insertSuccess) {
			jsonObject.addProperty("status", "success");

			jsonObject.addProperty("gb_p_num", gbPNum);
		} else {
			jsonObject.addProperty("status", "failure");
		}

		String message = gson.toJson(jsonObject);
		response.getWriter().write(message);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();

		int selectedProductId = Integer.parseInt(request.getParameter("selectedProductId")); // 获取从前端传来的值
		System.out.println(request.getParameter("selectedProductId"));
		int totalPNum = service.getTotalPNumForOrderId(selectedProductId);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("totalPNum", totalPNum);

		String message = gson.toJson(jsonObject);
		response.getWriter().write(message);
	}
}
