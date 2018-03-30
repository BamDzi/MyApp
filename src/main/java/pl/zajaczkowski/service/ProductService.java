package pl.zajaczkowski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.model.Role;
import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.CategoryRepository;
import pl.zajaczkowski.repository.ProductRepository;
import pl.zajaczkowski.repository.RoleRepository;

@Service						//("productService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;
	
	public void saveProduct(Product product) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		String userSession = auth.getName();
		User user = userService.findByEmail(userSession);
		
		
		product.setName(product.getName());
		product.setPurchasePrice(product.getPurchasePrice());
		product.setQuantity(product.getQuantity());
		product.setDescription(product.getDescription());
		product.setComponents(product.getComponents());
		product.setTips(product.getTips());
		product.setCategory(product.getCategory());
		product.setVendor(user);
		
		productRepository.save(product);
		
	}
	
	public Product findProductById(Integer id) {
		return productRepository.findById(id);
	}

	public Product findProductByName(String name) {
		return productRepository.findProductByName(name);
	}

	public List<Product> listAllProducts() {
		return productRepository.findAll();
	}

public void quantityIncrease(Product product) {
	
	product.setQuantity(product.getQuantity() - 1);
	
}
}																																
