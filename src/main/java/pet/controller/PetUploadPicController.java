package pet.controller;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pet.dao.PetDao;
import pet.dao.PetDaoImpl;
import pet.vo.Pet;

@MultipartConfig
@WebServlet("/petuploadpic")
public class PetUploadPicController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PetDao dao;

	@Override
	public void init() throws ServletException {
		dao = new PetDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		Pet pet = gson.fromJson(req.getReader(), Pet.class);
		int uid = pet.getUId();
		JsonObject responseJson = new JsonObject();
		// 讀取前端傳來的圖片資料
		Part filePart = req.getPart("petPhoto");
		InputStream fileContent = filePart.getInputStream();
		byte[] petPic = fileContent.readAllBytes();
		try {
			// 將圖片資料和其他相關資料一起存入資料庫
			// 實作 dao.savePetPic() 方法來處理存儲資料到資料庫的邏輯
//			dao.savePetPic(uid, petPic);
			responseJson.addProperty("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			responseJson.addProperty("status", "error");
		}
	}
}
