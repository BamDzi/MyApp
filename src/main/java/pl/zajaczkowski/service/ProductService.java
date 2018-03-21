package pl.zajaczkowski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.CategoryRepository;
import pl.zajaczkowski.repository.ProductRepository;

@Service						//("productService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
	public void saveProduct(Product product) {
		
//		final Category category = categoryRepository.findById(Integer.valueOf(2));
		
//		product = new Product();
		
		product.setName(product.getName());
		product.setPurchasePrice(product.getPurchasePrice());
		product.setQuantity(product.getQuantity());
		product.setDescription(product.getDescription());
		product.setComponents(product.getComponents());
		product.setTips(product.getTips());
		product.setCategory(product.getCategory());
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
}																																
