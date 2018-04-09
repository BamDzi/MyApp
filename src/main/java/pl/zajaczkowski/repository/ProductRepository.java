package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.model.User;


//@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findProductByName(String name);
	Product findProductByNameAndVendor(String name, User user);
	Product findById(Integer id);
	List<Product> findAll();
	List<Product> findByQuantityNotNull();
	List<Product> findProductByCategoryAndQuantityNotNull(Category category);
	List<Product> findProductByVendor(User user);
	
    

}
