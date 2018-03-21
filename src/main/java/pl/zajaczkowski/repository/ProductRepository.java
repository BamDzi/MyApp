package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.Product;


//@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findProductByName(String name);
	Product findById(Integer id);
	List<Product> findAll();
    

}
