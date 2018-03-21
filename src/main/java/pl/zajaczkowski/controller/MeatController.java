package pl.zajaczkowski.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Orders;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.OrderLineRepository;
import pl.zajaczkowski.repository.OrdersRepository;
import pl.zajaczkowski.service.ProductService;

@Controller
@RequestMapping("/meat")
public class MeatController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderLineRepository orderLineRepository;
	@Autowired
	private OrdersRepository ordersRepository;
//	@Autowired
//	private OrderLine orderLine;
//	@Autowired
	private Orders order = new Orders();

	
/*	public MeatController() {
	}
	public MeatController(ProductService productService) {
		this.productService = productService;
	}
*/
	
	@GetMapping//("meat")
	public String meat(@ModelAttribute OrderLine orderLine) {
		return "meat";
	}
	
	@GetMapping("increase")
	public String increase(@RequestParam Integer id, Model model) {

		Product product = productService.findProductById(id);
//		if()
//		OrderLine orderLine = new OrderLine();
		OrderLine orderLine = orderLineRepository.findById(2L);
		orderLine.setProduct(product);
		orderLine.setQuantity(9);
		orderLine.setPurchasePrice(product.getPurchasePrice());
		
		orderLineRepository.save(orderLine);
		
		order.getOrderLines().add(orderLine);
		
		return "redirect:/meat";
	}
	
	@ModelAttribute
	public void listProducts(Model model) {
		model.addAttribute("products", productService.listAllProducts());
	}

	@PostMapping("addOrder")
	public String addOrder() {
		
//		Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication();
		
		order.setDate(Calendar.getInstance());
//		order.setCustomer(customer);
		ordersRepository.save(order);
//		ordersRepository.saveAndFlush(order);
		return "redirect:/meat";
	}

}
