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


@WebServlet("/SelectFilterActCityController")
@MultipartConfig
public class SelectFilterActCityController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private TripService tripservice;

    public SelectFilterActCityController() {

    }

    //init()初始化頁面(必寫)，只要不執行生命週期的destroy()，它就永遠都會在
//    @Override
//    public void init() throws ServletException {
//        tripservice = new TripServiceImpl();
//    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //建立Gson物件，用於轉換JSON格式資料(前端傳來的資料)，與Java物件資料做轉換
        response.setContentType("application/json; charset=utf-8");
        Gson gson = new Gson();
        Trip trip = gson.fromJson(request.getReader(), Trip.class);

        System.out.println(trip.getT_act_city());

        List<Trip> trips = tripservice.showActCity(trip.getT_act_city());

        response.getWriter().write(gson.toJson(trips));
    }

}





