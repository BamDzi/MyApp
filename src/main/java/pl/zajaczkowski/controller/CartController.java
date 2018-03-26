package pl.zajaczkowski.controller;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import pl.zajaczkowski.repository.CustomerRepository;
import pl.zajaczkowski.repository.OrderLineRepository;
import pl.zajaczkowski.repository.OrdersRepository;
import pl.zajaczkowski.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private ProductService productService;
	private OrderLineRepository orderLineRepository;
	private OrdersRepository ordersRepository;

	public CartController(ProductService productService, OrderLineRepository orderLineRepository,
			OrdersRepository ordersRepository) {
		super();
		this.productService = productService;
		this.orderLineRepository = orderLineRepository;
		this.ordersRepository = ordersRepository;
	}

	@GetMapping()
	public String cart(OrderLine orderLine) {
		return "cart";
	}

	@RequestMapping("/addOrderLine")
	public String addToCart(@RequestParam Integer id, HttpServletRequest request, HttpSession session, Model model) {

		Product product = productService.findProductById(id);
		OrderLine orderLine = new OrderLine(); // Cart cart = new Cart();
		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("cart");
		
		if (list == null) {
			list = new LinkedHashSet<OrderLine>();
		}

		if (product != null) {
			orderLine.setProduct(product);
		}

		boolean isExist = false;

		for (OrderLine o : list) {
			if (o.equals(orderLine)) {
				o.setQuantity(o.getQuantity() + 1);
				isExist = true;
			}
		}
		if (isExist == false) {

			Long i = (long) (list.size() + 1);
			
			orderLine.setId(i);
			orderLine.setPurchasePrice(product.getPurchasePrice());
			orderLine.setQuantity(1);
			list.add(orderLine);
		}

		session = request.getSession();
		session.setAttribute("cart", list);

		return "redirect:/cart";
	}

	@RequestMapping("/orderLine/remove")
	public String removeOrderLine(@RequestParam Long id, HttpSession session) {

		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("cart");

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

		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("cart");

		for (OrderLine o : list) {
			if (o.getId() == id) {
				o.setQuantity(o.getQuantity() + 1);
			}
		}

		return "redirect:/cart";
	}

	@RequestMapping("/orderLine/decrease")
	public String decreaseOrderLine(@RequestParam Long id, HttpSession session) {

		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("cart");
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

		Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("cart");
		Orders order = new Orders();

		for (OrderLine o : list) {
			o.setId(null);
		}

		order.setOrderLines(list);

		orderLineRepository.save(list);
		ordersRepository.save(order);

		session.removeAttribute("cart");

		return "redirect:/cart";
	}

	@ModelAttribute("listOrderLine")
	public Set<OrderLine> listOrderLine(HttpSession session) {
		return (Set<OrderLine>) session.getAttribute("cart");
	}
}
