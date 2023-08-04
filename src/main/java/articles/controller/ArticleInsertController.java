package articles.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import articles.ariclesUtils.ArticlesUtils;
import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/ArtInsert")
@MultipartConfig
public class ArticleInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String art_user_id = request.getParameter("art_user_id");
		String art_title = request.getParameter("art_title");
		String art_content = request.getParameter("art_content");
		
		System.out.println("art_user_id="+art_user_id);
		System.out.println("art_title"+art_title);
		System.out.println("art_content"+art_content);
		
        List<byte[]> imageList = new ArrayList<>();

        // 獲取前端傳遞的多個 Part，即多張照片
        List<Part> imageParts = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().startsWith("image")) {
                imageParts.add(part);
            }
        }

        // 將每個 Part 的照片數據轉換為 byte[]
        for (Part imagePart : imageParts) {
            InputStream inputStream = imagePart.getInputStream();
            byte[] imageData = inputStream.readAllBytes();
            imageList.add(imageData);
            inputStream.close();
        }
        System.out.println("總共幾張照片="+imageList.size());

		int status = service.insertArticle(art_user_id, art_title, art_content,imageList);
		
		String jsonString = ArticlesUtils.TurnIntoJson(status);
		// 告訴前端response為json格式 編碼為UTF-8
		response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(jsonString);
	}

}