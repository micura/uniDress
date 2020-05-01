package hu.unideb.inf.Controller;

import hu.unideb.inf.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	private CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("categories", categoryService.getCategories());
		return "index";
	}

	@RequestMapping("/categories")
	public String categories(Model model){
		model.addAttribute("categories", categoryService.getCategories());
		return "categories";
	}

	@RequestMapping("/category/{id}")
	public String category(@PathVariable Long id, Model model) {
		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("products", categoryService.getProducts(id));
		return "category";
	}
}