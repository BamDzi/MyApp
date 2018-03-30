package pl.zajaczkowski.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.CategoryRepository;
import pl.zajaczkowski.repository.ProductRepository;
import pl.zajaczkowski.service.CategoryService;
import pl.zajaczkowski.service.ProductService;

@Controller
//@RequestMapping("/product")
//@ControllerAdvice	
public class ProductController {

	private ProductService productService;
//	@Autowired
//	private CategoryRepository categoryRepository;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
//		this.categoryRepository = categoryRepository;
	}

	@GetMapping("vendor")
	public String showVendorPage(Product product) {
		return "vendor/vendorpage";
	}

	@PostMapping("addproduct")
	public String addProduct(@Valid Product product, BindingResult bindingResult, ModelMap model) { //@Valid or @ModelAttribute Product product

		Product productExists = productService.findProductByName(product.getName());
		
		if (productExists != null) {
			bindingResult.rejectValue("product", "error.product",
					"There is already a product registered with the name provided");
		}
		
		if (bindingResult.hasErrors()) {
			return "redirect:/vendor";
		}
		
		
			productService.saveProduct(product);
//			productService.saveProduct(new Product());
//			model.addAttribute("successMessage", "Product has been added successfully");
//			model.addAttribute("product", new Product());
			
			
		
		return "redirect:/vendor";
	}
	
	@GetMapping("findAllProducts")
	public List<Product> findAllProducts() {
		return productService.listAllProducts();
	}
	
	@GetMapping("findProduct/{name}")
	public Product findProductName(@PathVariable String name) {
		return productService.findProductByName(name);
	}
	
	
}
