package pl.zajaczkowski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.CategoryRepository;
import pl.zajaczkowski.service.ProductService;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@ModelAttribute
	public void listProducts(Model model) {
		model.addAttribute("products", productService.listAllProducts());
	}
	
	  @ModelAttribute("allCategorys")
	    public List<Category> listCategorys() {
	      return categoryRepository.findAll();  
	    }

}
