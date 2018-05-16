package pl.zajaczkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.zajaczkowski.service.ProductService;

@Controller
// @RequestMapping("/product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("products")
	public String products(@RequestParam String name, Model model) {
		model.addAttribute("productsNotNull", productService.listProductByCategory(name));
		model.addAttribute("category", name);
		return "products";
	}

	@GetMapping("single")
	public String detail(Integer id, Model model) {
		model.addAttribute("productDetail", productService.findProductById(id));
		return "single";
	}
	
	// @GetMapping("findAllProducts")
	// public List<Product> findAllProducts() {
	// return productService.listAllProducts();
	// }

//	@GetMapping("findProduct/{name}")
//	public Product findProductName(@PathVariable String name) {
//		return productService.findProductByName(name);
//	}
}
