package tw.idv.tibame.activity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import tw.idv.tibame.activity.service.TripService;
import tw.idv.tibame.activity.service.impl.TripServiceImpl;
import tw.idv.tibame.activity.vo.Trip;


@WebServlet("/SelectActPicController")
@MultipartConfig
public class SelectActPicController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private TripService tripservice;

    //init()初始化頁面(必寫)，只要不執行生命週期的destroy()，它就永遠都會在
//    @Override
//    public void init() throws ServletException {
//        tripservice = new TripServiceImpl();
//    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("image/jpg");


        Integer T_act_id =Integer.parseInt( request.getParameter("T_act_id"));

        Trip trips =tripservice.showActPic(T_act_id);

        response.getOutputStream().write(trips.getT_act_pic_one());

        response.getOutputStream().flush();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //建立Gson物件，用於轉換JSON格式資料(前端傳來的資料)，與Java物件資料做轉換
//				Gson gson = new Gson();
//				Trip trip = gson.fromJson(request.getReader(), Trip.class);
//				System.out.println("SelectFilterHotActController");
//
//				try {
//		            Class.forName("com.mysql.cj.jdbc.Driver");
//		        } catch (ClassNotFoundException e) {
//		            e.printStackTrace();
//		        }

//				response.getWriter().write(gson.toJson(trip));


    }

}


