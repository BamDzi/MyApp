package pl.zajaczkowski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.Orders;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.OrdersRepository;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	private CustomerService customerService;


	public final List<Orders> listAllOrders() {
		return ordersRepository.findAll();
	}
	
	public Orders findById(Long id) {
		return ordersRepository.findById(id);
	}
	
	public final List<Orders> listOrdersByCustomer() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userSession = auth.getName();
		
		Customer customer = customerService.findByName(userSession);
		
		return ordersRepository.findByCustomer(customer);
	}
//public final List<Product> listProductByVendor() {
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String userSession = auth.getName();
//		
//		User vendor = userService.findByEmail(userSession);
//
//		if(vendor == null) {
//			//return exit
//		}
//		
//		return productRepository.findProductByVendor(vendor);
//	}
}
