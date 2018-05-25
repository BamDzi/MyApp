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
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void saveProduct(Product product) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		String userSession = auth.getName();
		User user = userService.findByEmail(userSession);
		
		product.setVendor(user);
		
		productRepository.save(product);
	}
	
	public void save(Product product) {
		productRepository.save(product);
	}
	
	
	public void updateProduct(Product product) {
//		productRepository.setProductInfoById(purchasePrice, quantity, description, components, tips, category, id);
	}
	
	public Product findProductById(Integer id) {
		return productRepository.findById(id);
	}

	public Product findProductByName(String name) {
		return productRepository.findProductByName(name);
	}
	
	public Product findProductByNameAndVendor(String name, User vendor) {
		return productRepository.findProductByNameAndVendor(name, vendor);
	}

	public List<Product> listAllProducts() {
		return productRepository.findAll();
	}
	
	public List<Product> listProductByCategory(String name) {
		Category category = categoryRepository.findByName(name);
		return productRepository.findProductByCategoryAndQuantityNotNullAndActiveTrue(category);//  find(category);
	}
	
	public final List<Product> listProductByVendorAndActiveTrue() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userSession = auth.getName();
		
		User vendor = userService.findByEmail(userSession);
		
		return productRepository.findProductByVendorAndActiveTrue(vendor);
	}
	
public final List<Product> listProductByVendorAndQuantityNull() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userSession = auth.getName();
		
		User vendor = userService.findByEmail(userSession);
		
		return productRepository.findProductByVendorAndQuantityNull(vendor);
	}
public final List<Product> listProductByVendorAndActiveFalse() {
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String userSession = auth.getName();
	
	User vendor = userService.findByEmail(userSession);
	
	return productRepository.findProductByVendorAndActiveFalse(vendor);
}

	public final List<Product> listProductByVendor() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userSession = auth.getName();
		
		User vendor = userService.findByEmail(userSession);

		if(vendor == null) {
			//return exit
		}
		
//		List<Product> productList = productRepository.findProductByVendor(vendor); 
		
		/*for(Product p : productList) {
			
		
			p.getCategory() = category.getName();
			
			
//			Category c = p.getCategory();
//			c.getName();
		}*/
//		return productList;
		return productRepository.findProductByVendor(vendor);
	}
	
}																																
