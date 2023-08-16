package tw.idv.tibame.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {

	@GetMapping(value = "generate-captcha", produces = MediaType.IMAGE_PNG_VALUE)
	public void generateCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
		int width = 200;
		int height = 60;
		int fontSize = 40; // 增大字型大小

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();

		String captcha = generateRandomString(4, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
		session.setAttribute("captchaText", captcha);
		for (int i = 0; i < captcha.length(); i++) {
			g2d.setColor(getRandomColor());
			g2d.setFont(new Font(getRandomFontName(), Font.PLAIN, fontSize));
			g2d.drawString(String.valueOf(captcha.charAt(i)), 20 + i * (fontSize + 5), 40);
		}

		g2d.dispose();

		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		ImageIO.write(image, "png", response.getOutputStream());

//	    System.out.println(response.getOutputStream());
	}

	@PostMapping(value = "check-captcha")
	public Map<String, Boolean> checkCaptcha(@RequestBody Map<String, String> requestBody, HttpSession session) {
		Map<String, Boolean> response = new HashMap<>();
		String userInput = requestBody.get("userInput");

		String captchaText = (String) session.getAttribute("captchaText"); // 从 session 中获取生成的验证码

		System.out.println(captchaText);

		boolean isValid = captchaText != null && captchaText.equalsIgnoreCase(userInput);

		response.put("valid", isValid);
		System.out.println(isValid);
		return response;
	}

	private String generateRandomString(int length, String characters) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	private Color getRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}

	private String getRandomFontName() {
		String[] fontNames = { "Arial", "Times New Roman", "Courier New"};
		Random random = new Random();
		return fontNames[random.nextInt(fontNames.length)];
	}
}
