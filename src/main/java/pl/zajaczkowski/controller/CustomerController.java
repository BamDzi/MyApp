package pl.zajaczkowski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.repository.CustomerRepository;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("findAllCustomers")
	List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	@GetMapping("findCustomerById/{id}")
	Customer findById(@PathVariable Long id) {
		return customerRepository.findById(id);
		
	}
	
//	@GetMapping("findCustomer/{name}")
//	public Customer findByName(@PathVariable String name) {
//		return customerRepository.findByName(name);
//	}
	
	
	
}
