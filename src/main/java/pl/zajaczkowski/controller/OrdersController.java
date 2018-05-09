package pl.zajaczkowski.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Orders;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.OrdersRepository;
import pl.zajaczkowski.service.OrdersService;
import pl.zajaczkowski.service.ProductService;


@Controller
public class OrdersController {
	
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrdersService ordersService;
	
	@GetMapping("ordersList")
	public String ordersList(Model model) {
		model.addAttribute("allOrders", ordersService.listOrdersByCustomer());
//		model.addAttribute("ordersByCustomer", ordersService.listOrdersByCustomer());
		return "ordersList";
	}
	
	@GetMapping("orderView")
	public String orderView(Long id, Model model) {
		
		Orders order = ordersRepository.findById(id);
		Set<OrderLine> orderLines = order.getOrderLines(); 
		model.addAttribute("listMyOrderLine", orderLines);    /*.stream().collect(Collectors.toList()));*/
		return "orderView";
	}
	
	
//	@GetMapping("products")
//	public String products(@RequestParam String name, Model model) {
//		model.addAttribute("productsNotNull", productService.listProductByCategory(name));
//		model.addAttribute("category", name);
//		return "products";
//	}

	@GetMapping("findAllOrders")
	List<Orders> findAll() {
		return ordersRepository.findAll();
	}
	
//	@GetMapping("/order/details/{id}")
//	Orders findById(@RequestParam Long id) {
//		return ordersRepository.findById(id);
//	}
	
	@GetMapping("customerOrders/{id}")
	List<Orders> findByCustomer(@PathVariable Long id) {
		return ordersRepository.findByCustomerId(id);
	}
	
//	@ModelAttribute
//	public void listProducts(Model model) {
//		model.addAttribute("products", productService.listAllProducts());
//	}

}
