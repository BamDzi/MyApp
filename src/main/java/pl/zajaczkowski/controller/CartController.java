package pl.zajaczkowski.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Orders;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.CustomerRepository;
import pl.zajaczkowski.repository.OrderLineRepository;
import pl.zajaczkowski.service.ProductService;

@Controller
//@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderLineRepository orderLineRepository;
	
	@GetMapping("cart")
	public String cart(OrderLine orderLine) {
		return "cart";
	}
	
	  @RequestMapping("/cart/add")
	    public String addCart(@RequestParam Integer id, HttpSession session, Model model) {

		  	Product product = productService.findProductById(id); 	//Book book = bookDao.get(id);
		  	OrderLine orderLine = new OrderLine(); 					//Cart cart = new Cart();
//	        Set<OrderLine> list = (Set<OrderLine>) session.getAttribute("cart");	//List<Cart> list = (List<Cart>) session.getAttribute("cart");
	        
//	        if (list == null) {
//	            list = new ArrayList<OrderLine>();//new LinkedHashSet<OrderLine>();			//new ArrayList<Cart>();
//	        }
	        if (product != null) {
	            orderLine.setProduct(product);  				//cart.ToCart(book);
//	            orderLine.setQuantity(3);
//	            BigDecimal total = addToCart(list, orderLine);
//	            model.addAttribute("total", list);	//mav.addObject("total", total);
	            model.addAttribute(orderLine);
//	            session.setAttribute("cart", list);
	        }
	        
	        boolean isExit = false;
	        List<OrderLine> orderLineList = orderLineRepository.findAll();
	        for (OrderLine o : orderLineList) {
//	        	if(o.equals(orderLine)) {
	        	if(o.getProduct() == orderLine.getProduct()) {
	        		o.setPurchasePrice(product.getPurchasePrice());
	        		o.setQuantity(o.getQuantity() + 1);
//	        		orderLine.setPurchasePrice(new BigDecimal("11.98"));
//	        		orderLine.setQuantity(orderLine.getQuantity() + 1);
	        		orderLineRepository.save(o);
	        		isExit = true;
	        	}
	        }
	        if(isExit == false) {
	        	orderLine.setPurchasePrice(product.getPurchasePrice());
        		orderLine.setQuantity(1);
	        	orderLineRepository.save(orderLine);										//mav.addObject("listCart", list);
	        }
	        
	        return "redirect:/meat";
	    }
/*
	    private static void addToCart(final List<OrderLine> list, final OrderLine orderLine) {
//	        BigDecimal total = new BigDecimal(0);
	        boolean isExit = false;
	        for (OrderLine c : list) {
	            if (c.equals(orderLine)) {
	                c.setQuantity(c.getQuantity() + 1);
	                isExit = true;
	            }
//	            total = total.add(c.getPurchasePrice().multiply(new BigDecimal(c.getQuantity())));
	        }
	        if (isExit == false) {
	            list.add(orderLine);
//	            total = total.add(orderLine.getPurchasePrice().multiply(new BigDecimal(orderLine.getQuantity())));
	        }
//	        return total;
	    }
*//*
	    @RequestMapping("/shop/cart/remove")
	    public ModelAndView removeCart(@RequestParam int id, HttpSession session) {
	        ModelAndView mav = new ModelAndView("shop/cart");
	        List<Cart> list = (List<Cart>) session.getAttribute("cart");
	        if (list != null) {
	            BigDecimal total = removeCartItem(list, id);
	            mav.addObject("total", total);
	            session.setAttribute("cart", list);
	        }
	        mav.addObject("listCart", list);
	        return mav;
	    }

	    private BigDecimal removeCartItem(List<Cart> list, int id) {
	        BigDecimal total = new BigDecimal(0);
	        Cart temp = null;
	        for (Cart c : list) {
	            if (c.getId() == (id)) {
	                temp = c;
	                continue;
	            }
	            total = total.add(c.getPrice().multiply(new BigDecimal(c.getQuantity())));
	        }
	        if (temp != null)
	            list.remove(temp);
	        return total;
	    }

	    @RequestMapping("/shop/cart/update")
	    public ModelAndView updateCart(@RequestParam int id,
	                                   @RequestParam int quantity,
	                                   HttpSession session) {
	        ModelAndView mav = new ModelAndView("shop/cart");
	        List<Cart> list = (List<Cart>) session.getAttribute("cart");
	        if (list != null) {
	            BigDecimal total = updateCartItem(list, id, quantity);
	            mav.addObject("total", total);
	            session.setAttribute("cart", list);
	        }
	        mav.addObject("listCart", list);
	        return mav;
	    }

	    private BigDecimal updateCartItem(List<Cart> list, int id, int quantity) {
	        BigDecimal total = new BigDecimal(0);
	        for (Cart c : list) {
	            if (c.getId() == (id)) {
	                c.setQuantity(quantity);
	            }
	            total = total.add(c.getPrice().multiply(new BigDecimal(c.getQuantity())));
	        }
	        return total;
	    }*/


/*		@Autowired
	private CustomerRepository customerRepository;
	private Orders order = new Orders();
//	addOrderLine
//	removeOrderLine
//	updateOrderLine
//	save Order and clearCart 
	
	@PostMapping("addOrderLine")
	public void addOrderLine(@ModelAttribute OrderLine orderLine, HttpServletRequest request, HttpSession session) {

//		final OrderLine orderLine = new OrderLine();
//	    orderLine.setProduct(prod2);
	    orderLine.setQuantity(orderLine.getQuantity());
//	    orderLine.setPurchasePrice(prod2.getPrice());

	    order.getOrderLines().add(orderLine);

		
	}

	@GetMapping("addCart")
    public void addCart(HttpServletRequest request, HttpSession session) {

//		Customer customer = customerRepository.findByName(name);
//		order.setCustomer(customer);
		
		session = request.getSession(true);
		session.setAttribute("order", order);
		
		
    	session = request.getSession(true);
    	session.setAttribute("MySessionVariable", param);
    	
        Book book = bookDao.get(id);
        Cart cart = new Cart();
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        if (list == null) {
            list = new ArrayList<Cart>();
        }
        if (book != null) {
            cart.ToCart(book);
            BigDecimal total = addToCart(list, cart);
            mav.addObject("total", total);
            session.setAttribute("cart", list);
        }
        mav.addObject("listCart", list);
    }
*/
}
