package tw.idv.tibame.activity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.tibame.activity.service.TripService;
import tw.idv.tibame.activity.service.impl.TripServiceImpl;
import tw.idv.tibame.activity.vo.Trip;


@WebServlet("/SelectFilterNewActController")
public class SelectFilterNewActController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TripService tripservice;

    public SelectFilterNewActController() {

    }

    //init()初始化頁面(必寫)，只要不執行生命週期的destroy()，它就永遠都會在
    @Override
    public void init() throws ServletException {
        tripservice = new TripServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //建立Gson物件，用於轉換JSON格式資料(前端傳來的資料)，與Java物件資料做轉換
        response.setContentType("application/json; charset=utf-8");

        String TypeString = request.getParameter("Type");

        System.out.println(TypeString);

        Gson gson = new Gson();
//		Trip trip = gson.fromJson(request.getReader(), Trip.class);
        //System.out.println("SelectFilterNewActController");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        List<Trip> trips = null;

        if( "0".equals(TypeString)) {
            trips = tripservice.showNewAct();
        }else {
            trips = tripservice.showHotAct();
        }



        response.getWriter().write(gson.toJson(trips));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }
}
