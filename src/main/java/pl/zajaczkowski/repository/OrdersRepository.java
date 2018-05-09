package pl.zajaczkowski.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.OrderLine;
import pl.zajaczkowski.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
	
	List<Orders> findAll();
    Orders findById(Long id);
    List<Orders> findByCustomerId(Long customerId);
	List<Orders> findByCustomer(Customer customer);
}
