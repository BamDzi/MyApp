package pl.zajaczkowski.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.service.ProductService;

@Controller
public class ProductController {
	
//	private ProductRepository productRepository;
	private ProductService productService;
	
//	@Autowired
	public ProductController(ProductService productService) {
//		this.productRepository = productRepository;
		this.productService = productService;
	}
	
	@GetMapping("vendor")
	public String showVendorPage(@ModelAttribute Product product) {
		return "vendor/vendorpage";
	}

	@PostMapping("addproduct")
	public String addProduct(@Valid Product product, BindingResult bindingResult, Model model) {

		Product productExists = productService.findProductByName(product.getName());
		
		if (productExists != null) {
			bindingResult.rejectValue("product", "error.product",
					"There is already a product registered with the name provided");
		}
		
		if (bindingResult.hasErrors()) {
			return "redirect:/vendor";
		}
		
			productService.saveProduct(product);
			model.addAttribute("successMessage", "Product has been added successfully");
			model.addAttribute("product", new Product());
		
		return "redirect:/vendor";
	}
	
	@ModelAttribute
	public void listProducts(Model model) {
		model.addAttribute("products", productService.listAllProducts());
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

}
