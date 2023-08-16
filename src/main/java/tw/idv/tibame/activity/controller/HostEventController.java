package tw.idv.tibame.activity.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.tibame.activity.service.TripService;
import tw.idv.tibame.activity.service.impl.TripServiceImpl;
import tw.idv.tibame.activity.vo.Trip;

@WebServlet("/HostEventController")//註冊Servlet
@MultipartConfig
public class HostEventController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Gson gson = new Gson();
    private TripService tripservice;

    @Override
    public void init() throws ServletException {
        tripservice = new TripServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        Trip trip = gson.fromJson(request.getReader(), Trip.class);
        final String tActPicOneBase64 = trip.gettActPicOneBase64();
        if (tActPicOneBase64 != null && !tActPicOneBase64.isEmpty()) {
            final byte[] xxx = Base64.getDecoder().decode(tActPicOneBase64);
            trip.setT_act_pic_one(xxx);
        }
        tripservice.hostEvent(trip);
        response.getWriter().write(gson.toJson(trip));
    }
}


