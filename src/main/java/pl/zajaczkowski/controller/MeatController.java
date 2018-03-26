package pl.zajaczkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.zajaczkowski.service.ProductService;

@Controller
@RequestMapping("/meat")
public class MeatController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String meat() {
		return "meat";
	}

//	In future view list products from meat category
	@ModelAttribute
	public void listProducts(Model model) {
		model.addAttribute("products", productService.listAllProducts());
	}

}
