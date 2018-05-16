package pl.zajaczkowski.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Orders;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.OrdersRepository;
import pl.zajaczkowski.repository.UserRepository;
import pl.zajaczkowski.service.OrdersService;
import pl.zajaczkowski.service.ProductService;
import pl.zajaczkowski.service.UserService;

@Controller
public class CommonController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersRepository ordersRepository;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private UserRepository userRepository;

	@GetMapping("/")
	public String startPage() {
		
//		User user = userService.findByEmail("pzajac82@gmail.com"); 
//		user.setActive(true);
//		userRepository.save(user);
		
//		Orders order = ordersRepository.findById(1L);
////		Set<OrderLine> orderLines = new LinkedHashSet<OrderLine>(); 
//		Set<OrderLine> orderLines = order.getOrderLines();
//		
//		System.out.println("Zwiera11");
//		System.out.println("Zwiera12");
//		for (OrderLine o : orderLines) {
//System.out.println(o.getId());
//System.out.println(o.getPurchasePrice());
//		}
//		System.out.println("Zwiera12");
//		System.out.println("Zwiera12");
		return "home";
	}
	
	@GetMapping("settings")
	public String customer() {
		return "settings";
	}

	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("regulations")
	public String regulations() {
		return "regulations";
	}
	
	@GetMapping("aboutus")
	public String aboutUs() {
		return "aboutUs";
	}
	
	@GetMapping("infoVendor")
	public String infoVendor() {
		return "infoVendor";
	}
	
}
