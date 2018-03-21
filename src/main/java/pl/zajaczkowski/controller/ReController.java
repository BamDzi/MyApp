/*package pl.zajaczkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.OrderLineRepository;
import pl.zajaczkowski.service.ProductService;

@RestController
public class ReController {
	
	@Autowired
	private ProductService productService; 
	@Autowired
	private OrderLineRepository orderLineRepository;
	
	@RequestMapping("meat")
	public void increase(Model model) { //(@RequestParam Integer id) {

		Product product = productService.findProductById(4);
		OrderLine orderLine = new OrderLine();
		orderLine.setProduct(product);
		orderLine.setQuantity(product.getId());
		orderLine.setPurchasePrice(product.getPurchasePrice());
		
		orderLineRepository.save(orderLine);
		
//		order.getOrderLines().add(orderLine);
	}

}
*/