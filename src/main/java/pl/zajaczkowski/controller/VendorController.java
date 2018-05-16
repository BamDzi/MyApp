package pl.zajaczkowski.controller;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.model.User;
import pl.zajaczkowski.model.Vendor;
import pl.zajaczkowski.repository.CategoryRepository;
import pl.zajaczkowski.repository.VendorRepository;
import pl.zajaczkowski.service.ProductService;
import pl.zajaczkowski.service.UserService;

@Controller
@RequestMapping("/vendor")
public class VendorController {
	
	private ProductService productService;
	private UserService userService;
	private CategoryRepository categoryRepository;
	private VendorRepository vendorRepository;
	
	public VendorController(ProductService productService, UserService userService,
			CategoryRepository categoryRepository, VendorRepository vendorRepository) {
		super();
		this.productService = productService;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
		this.vendorRepository = vendorRepository;
	}

	@GetMapping
	public String showVendorPage(@ModelAttribute Product product, Model model) {
		model.addAttribute("allVendorProducts", productService.listProductByVendorAndQuantityNotNull());
		return "vendor/vendorpage";
	}
	
	@GetMapping("newproduct")
	public String addProduct(@ModelAttribute Product product, Model model) {
		model.addAttribute("allCategorys", categoryRepository.findAll());
		return "vendor/newProduct";
	}

	@PostMapping("newproduct")
	public String newProduct(@ModelAttribute Product product, BindingResult bindingResult) {//, ModelMap model) { // @Valid or
																									// @ModelAttribute
																									// Product product

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userSession = auth.getName();

		User vendor = userService.findByEmail(userSession);

		// Product productExists = productService.findProductByName(product.getName());

		Product productExists = productService.findProductByNameAndVendor(product.getName(), vendor);

		if (productExists != null) {
			bindingResult.rejectValue("product", "error.product",
					"There is already a product registered with the name provided");
		}

		if (bindingResult.hasErrors()) {
			return "redirect:/vendor/newproduct";
		}

		productService.saveProduct(product);
		// productService.saveProduct(new Product());
		// model.addAttribute("successMessage", "Product has been added successfully");
		// model.addAttribute("product", new Product());

		return "redirect:/vendor/newproduct";
	}
	
	@GetMapping("archiveproducts")
	public String archiveProducts(@ModelAttribute Product product, Model model) {
		model.addAttribute("allVendorNullProducts", productService.listProductByVendorAndQuantityNull());
		return "vendor/archiveProducts";
	}
	
	@GetMapping("history")
	public String historyOfShopping() {
		return "vendor/history";
	}
	
	@GetMapping("info_of_vendor")
	public String aboutVendor(@ModelAttribute Vendor vendor, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userSession = auth.getName();

		User user = userService.findByEmail(userSession);
		vendor = vendorRepository.findVendorByUser(user);
		
		if(vendor == null) {
			vendor = new Vendor();
			vendor.setUser(user);
			vendorRepository.save(vendor);
		}
		
		model.addAttribute(vendor);

		return "vendor/aboutVendor";
	}

	@PostMapping("info_of_vendor")
	public String infoVendor(@ModelAttribute Vendor vendor, BindingResult bindingResult){//, ModelMap model) {
		
		if (bindingResult.hasErrors()) {
			return "redirect:/vendor/info_of_vendor";
		}

		vendorRepository.save(vendor);

		return "redirect:/vendor/info_of_vendor";
	}
	
	@GetMapping("product/edit")
	public String editProduct(@RequestParam Integer id, @ModelAttribute Product product, Model model) {
		
		product = productService.findProductById(id);
		
		model.addAttribute("allCategorys", categoryRepository.findAll());
		model.addAttribute(product);
		
		return "vendor/editProduct";
	}
	
	@PostMapping("product/edit")
	public String saveChange(@ModelAttribute Product product, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "redirect:/vendor/info_of_vendor";
		}
		
		productService.saveProduct(product);
		
		return "redirect:/vendor";
	}
	
	@GetMapping("product/remove")
	public String removeProduct(@RequestParam Integer id) {
		
		Product product = productService.findProductById(id);
		if(product != null) {
			product.setQuantity(null);
		productService.saveProduct(product);
		}
		return"redirect:/vendor";
	}
}
