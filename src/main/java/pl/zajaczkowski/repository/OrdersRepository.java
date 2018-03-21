package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
	
	List<Orders> findAll();
    Orders findById(Long id);
    List<Orders> findByCustomerId(Long customerId);

}
