package pl.zajaczkowski.controller;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Orders;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.CustomerRepository;
import pl.zajaczkowski.repository.OrderLineRepository;
import pl.zajaczkowski.repository.OrdersRepository;
import pl.zajaczkowski.service.ProductService;
import pl.zajaczkowski.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private ProductService productService;
	private OrderLineRepository orderLineRepository;
	private OrdersRepository ordersRepository;
	private CustomerRepository customerRepository;
	private UserService userService;
	
	public CartController(ProductService productService, OrderLineRepository orderLineRepository,
			OrdersRepository ordersRepository, CustomerRepository customerRepository, UserService userService) {
		super();
		this.productService = productService;
		this.orderLineRepository = orderLineRepository;
		this.ordersRepository = ordersRepository;
		this.customerRepository = customerRepository;
		this.userService = userService;
	}

	@GetMapping()
	public String cart(OrderLine orderLine) {
		return "cart";
	}

	@GetMapping("submit")
	public String submit() {
		return "submit";
	}
	
	@RequestMapping("/addOrderLine")
	public String addToCart(@RequestParam Integer id, HttpServletRequest request, HttpSession session, Model model) {

		Product product = productService.findProductById(id);
		BigDecimal productPrice = new BigDecimal("0.0");
		productPrice = product.getPurchasePrice();
		OrderLine orderLine = new OrderLine(); // Cart cart = new Cart();
		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("orderLines");
//		Set<Product> productsList = (Set<Product>) session.getAttribute("productsList");

		if (list == null) {
			list = new LinkedHashSet<OrderLine>();
//			productsList = new LinkedHashSet<Product>();
		}

		boolean isExist = false;
		
		if (product != null) {
			orderLine.setProduct(product);
		}

		if (list.contains(orderLine)) {
			System.out.println("Zwiera");
			System.out.println("Zwiera");
			System.out.println("Zwiera");
			System.out.println("Zwiera");
		}
		
		for (OrderLine o : list) {
			if (o.equals(orderLine)) {
				o.setQuantity(o.getQuantity() + 1);
				o.setAmount(o.getAmount().add(productPrice));
				isExist = true;
			}
		}
		
		if (isExist == false) {

//			Long i = (long) (list.size());
		
			if (list.isEmpty()) {
				orderLine.setId(1L);
			}
			
			Long temp = null;
			for (OrderLine o : list) {
				temp = o.getId();
			}
			
			if (list.isEmpty() == false) {
			orderLine.setId(temp + 1);
			}
			
			orderLine.setPurchasePrice(productPrice);
			orderLine.setQuantity(1);
			orderLine.setAmount(productPrice);
			list.add(orderLine);
		}

		session = request.getSession();
		session.setAttribute("orderLines", list);
//		session.setAttribute("productsList", productsList);

		return "redirect:/cart";
	}

	@RequestMapping("/orderLine/remove")
	public String removeOrderLine(@RequestParam Long id, HttpSession session) {

		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("orderLines");

		OrderLine temp = null;

		for (OrderLine o : list) {
			if (o.getId() == id) {
				temp = o;
				continue;
			}
		}
		if (temp != null) {
			list.remove(temp);
		}

		return "redirect:/cart";
	}

	@RequestMapping("/orderLine/increase")
	public String increaseOrderLine(@RequestParam Long id, HttpSession session) {

		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("orderLines");

		for (OrderLine o : list) {
			if (o.getId() == id) {
//			if (o.equals(id)) {
				o.setQuantity(o.getQuantity() + 1);
				o.setAmount(o.getAmount().add(o.getPurchasePrice()));
			}
		}

		return "redirect:/cart";
	}

	@RequestMapping("/orderLine/decrease")
	public String decreaseOrderLine(@RequestParam Long id, HttpSession session) {

		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("orderLines");
		OrderLine temp = null;

		for (OrderLine o : list) {
			if (o.getId() == id) {
				o.setQuantity(o.getQuantity() - 1);
				if (o.getQuantity() == 0) {
					temp = o;
				}
			}
		}

		if (temp != null) {
			list.remove(temp);
		}

		return "redirect:/cart";
	}

	@GetMapping("submitOrder")
	public String saveOrder(HttpSession session, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userSession = auth.getName();
		Customer customer = customerRepository.findByName(userSession);
		if (customer == null) {
			customer = new Customer();
			User user = userService.findByEmail(userSession);
			if (user != null) {
				customer.setUser(user);
			} else {
				customer.setName(userSession);
			}
			customerRepository.save(customer);
		}
		
		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("orderLines");
//		Set<Product> productsList = (Set<Product>) session.getAttribute("productsList");
		Orders order = new Orders();

		for (OrderLine o : list) {
			o.setId(null);
		}

		// order.setOrderLines(list);
		order.setOrderLines(new LinkedHashSet<OrderLine>(list));
		order.setCustomer(customer);
		
		orderLineRepository.save(list);
		ordersRepository.save(order);

//		for(Product p : productsList) {
//			productService.saveProduct(p);
//		}
		
//		productService.saveProduct(product);
		
		session.removeAttribute("orderLines");
//		session.removeAttribute("productsList");

		return "redirect:/cart";
	}

	@ModelAttribute("listOrderLine")
	public Set<OrderLine> listOrderLine(HttpSession session) {
		return (Set<OrderLine>) session.getAttribute("orderLines");
	}
}
