package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findAll();
	Customer findById(Long id);
	Customer findByUser(User user);
	Customer findByName(String name);
}
