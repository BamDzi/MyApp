package pl.zajaczkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.service.ProductService;

@Controller
public class CommonController {

//	@Autowired
//	private ProductService productService;

	@GetMapping
//	@RequestMapping("/")
	public String startPage() {
		return "home";
	}
	
	@GetMapping("settings")
	public String customer() {
		return "settings";
	}

	@GetMapping("single")
	public String detail() {
		return "single";
	}

	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("regulations")
	public String regulations() {
		return "regulations";
	}
	
	@GetMapping("ordersList")
	public String ordersList() {
		return "ordersList";
	}

}
