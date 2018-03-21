/*package pl.zajaczkowski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.zajaczkowski.model.Orders;
import pl.zajaczkowski.repository.OrdersRepository;
import pl.zajaczkowski.service.ProductService;


@Controller
public class OrdersController {
	
	private OrdersRepository ordersRepository;
	@Autowired
	private ProductService productService;
	
	@PostMapping("addOrderLine")
	void addOrderLine() {
		
	}
	
	@GetMapping("findAllOrders")
	List<Orders> findAll() {
		return ordersRepository.findAll();
	}
	
	@GetMapping("findOrder/{id}")
	Orders findById(@PathVariable Long id) {
		return ordersRepository.findById(id);
	}
	
	@GetMapping("customerOrders/{id}")
	List<Orders> findByCustomer(@PathVariable Long id) {
		return ordersRepository.findByCustomerId(id);
	}
	
	@ModelAttribute
	public void listProducts(Model model) {
		model.addAttribute("products", productService.listAllProducts());
	}

}
*/