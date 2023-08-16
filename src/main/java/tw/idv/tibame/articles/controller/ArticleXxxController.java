package tw.idv.tibame.articles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticleXxxController {

	@GetMapping("/articleXxx")
	public String sendtoArticle(@RequestParam Integer artId, RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("artId", artId); // 由於article.html頁面載入會發請求到別的controller

		return "redirect:/article.html"; // 如果要使用redirect或forward就不能用@RestController

	}

}
