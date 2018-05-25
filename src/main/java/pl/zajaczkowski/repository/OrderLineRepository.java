package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Product;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

	OrderLine findById(Long id);
//	List<OrderLine> findOrderLineByProduct(Product product);
	List<OrderLine> findByProduct(List<Product> products);
	
}
