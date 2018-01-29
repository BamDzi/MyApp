package pl.zajaczkowski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.repository.ProductRepository;

@Service("productService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void saveProduct(Product product) {
		product.setName(product.getName());
		product.setPrice(product.getPrice());
		product.setAmount(product.getAmount());
		product.setDescription(product.getDescription());
		product.setComponents(product.getComponents());
		product.setTips(product.getTips());
		productRepository.save(product);
		
	}

	public Product findProductByName(String name) {
		return productRepository.findProductByName(name);
	}

	public List<Product> listAllProducts() {
		return productRepository.findAll();
	}
}																																
