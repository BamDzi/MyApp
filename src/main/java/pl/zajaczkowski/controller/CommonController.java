package pl.zajaczkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.service.ProductService;

@Controller
public class CommonController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public String startPage() {
		return "default";
	}


	@GetMapping("sausage")
	public String sausage() {
		return "sausage";
	}

	@GetMapping("fish")
	public String fish() {
		return "fish";
	}

	@GetMapping("dairy")
	public String dairy() {
		return "dairy_product";
	}

	@GetMapping("settings")
	public String settings() {
		return "settings";
	}

	@GetMapping("viewProduct")
	public String detail() {
		return "viewProduct";
	}

	@GetMapping("contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("submit")
	public String submit() {
		return "submit";
	}

	@GetMapping("default")
	public String template() {
		return "default";
	}

	@GetMapping("test")
	public String templorary() {
		return "defaultold";
	}

}
