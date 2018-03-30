package pl.zajaczkowski.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.zajaczkowski.model.Customer;
import pl.zajaczkowski.model.Role;
import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.RoleRepository;
import pl.zajaczkowski.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveRegistrationUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		user.setActive(false);
		// Generate random 36-character string token for confirmation link
		user.setConfirmationToken(UUID.randomUUID().toString());
		Role userRole = roleRepository.findByRole("CUSTOMER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	

}
