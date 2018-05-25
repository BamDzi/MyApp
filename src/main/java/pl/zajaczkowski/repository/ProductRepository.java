package pl.zajaczkowski.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.model.Product;
import pl.zajaczkowski.model.User;


//@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findProductByName(String name);
	Product findProductByNameAndVendor(String name, User user);
	Product findById(Integer id);
	List<Product> findAll();
	List<Product> findByActiveFalse();
	List<Product> findByQuantityNotNull();
	List<Product> findByQuantityNotNullAndActiveTrue();
	List<Product> findProductByCategoryAndQuantityNotNullAndActiveTrue(Category category);
	List<Product> findProductByVendor(User user);
	List<Product> findProductByVendorAndQuantityNotNull(User user);
	List<Product> findProductByVendorAndQuantityNull(User user);
	List<Product> findProductByVendorAndActiveFalse(User user);
	List<Product> findProductByVendorAndActiveTrue(User user);
	
//	@Modifying
//	@Query("update Product p set p.purchasePrice = ?1, p.quantity = ?2, p.description = ?3, p.components = ?4, p.tips = ?5, p.category = ?6, where p.id = ?7")
//	void setProductInfoById(BigDecimal purchasePrice, Integer quantity, String description, String components, String tips, Category category, Integer id);
    

}
