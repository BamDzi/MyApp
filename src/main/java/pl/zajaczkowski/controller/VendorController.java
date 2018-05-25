package pl.zajaczkowski.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import pl.zajaczkowski.repository.OrderLineRepository;
import pl.zajaczkowski.repository.ProductAuditRepository;
import pl.zajaczkowski.repository.VendorAuditRepository;
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
	private ProductAuditRepository productAuditRepository;
	private VendorAuditRepository vendorAuditRepository;
	private OrderLineRepository orderLineRepository;

	public VendorController(ProductService productService, UserService userService,
			CategoryRepository categoryRepository, VendorRepository vendorRepository,
			ProductAuditRepository productAuditRepository, VendorAuditRepository vendorAuditRepository,
			OrderLineRepository orderLineRepository) {
		super();
		this.productService = productService;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
		this.vendorRepository = vendorRepository;
		this.productAuditRepository = productAuditRepository;
		this.vendorAuditRepository = vendorAuditRepository;
		this.orderLineRepository = orderLineRepository;
	}

	@GetMapping
	public String showVendorPage(@ModelAttribute Product product, Model model) {
		model.addAttribute("allVendorActiveProducts", productService.listProductByVendorAndActiveTrue());
		return "vendor/vendorpage";
	}
	
	@GetMapping("newproduct")
	public String addProduct(@ModelAttribute Product product, Model model) {
		model.addAttribute("allCategorys", categoryRepository.findAll());
		return "vendor/newProduct";
	}

	@PostMapping("newproduct")
	public String newProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {//, ModelMap model) { // @Valid or

		Product productExists = productService.findProductByName(product.getName());

		if (productExists != null) {
			
			model.addAttribute("productExists", "Taki produkt istnieje");
//			bindingResult.rejectValue("name", "result returns more than one elements", "There is already a product registered with the name provided");
		}

		if (bindingResult.hasErrors()) {
			return "redirect:/vendor/newproduct";
		}
		
		productService.saveProduct(product);
		
		return "redirect:/vendor/newproduct";
	}
	
	@GetMapping("product/edit")
	public String editProduct(@RequestParam Integer id, @ModelAttribute Product product, Model model) {
		
		product = productService.findProductById(id);
		
//		model.addAttribute("allCategorys", categoryRepository.findAll());
		model.addAttribute(product);
		
		return "vendor/editProduct";
	}
	
	@PostMapping("product/edit")
	public String saveChange(@RequestParam Integer id, @ModelAttribute Product product, BindingResult bindingResult) {
		
		Product productExist = productService.findProductById(product.getId());
		
		if (bindingResult.hasErrors()) {
			return "redirect:/vendor/info_of_vendor";
		}
		
		productExist.setPurchasePrice(product.getPurchasePrice());
		productExist.setQuantity(product.getQuantity());
		productExist.setDescription(product.getDescription());
		productExist.setComponents(product.getComponents());
		productExist.setTips(product.getTips());
		
		productService.save(productExist);
		
		return "redirect:/vendor";
	}
	
	@GetMapping("archive_products")
	public String archiveProducts(@ModelAttribute Product product, Model model) {
		model.addAttribute("allVendorNullProducts", productService.listProductByVendorAndActiveFalse());
		return "vendor/archiveProducts";
	}
	
	@GetMapping("send_to_active")
	public String sendToActive(@RequestParam Integer id, @ModelAttribute Product product) {//, Model model) {
		
		product = productService.findProductById(id);
		product.setActive(true);
		productService.saveProduct(product);
		
		return "redirect:/vendor/archive_products";
	}
	
	@GetMapping("history")
	public String historyOfShopping(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		String userSession = auth.getName();
		User user = userService.findByEmail(userSession);
		
//		String userId = (String) user.getId();
		
		model.addAttribute("productAudit", productAuditRepository.findByUserId(user.getId()));
		model.addAttribute("vendorAudit", vendorAuditRepository.findByVendorId(user.getId()));
		
		List<Product> products = productService.listProductByVendor();
		for(int i = 0; i<10; i++) {
			System.out.println("i");
		}
		for(Product pr : products) {
			System.out.println(pr.getName());
		}
		for(int i = 0; i<10; i++) {
			System.out.println("i");
		}
		
//		model.addAttribute("allOrderLine", orderLineRepository.findByProduct(products));
		
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
	
	@GetMapping("product/remove")
	public String removeProduct(@RequestParam Integer id) {
		
		Product product = productService.findProductById(id);
		if(product != null) {
			product.setActive(false);
			productService.save(product);
		}
		return"redirect:/vendor";
	}
}
