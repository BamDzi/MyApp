package pl.zajaczkowski.service;

import org.springframework.stereotype.Service;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public Customer findByName(String name) {
		return customerRepository.findByName(name);
	}

	public Customer findByUser(User user) {
		return customerRepository.findByUser(user);
	}
	

}
