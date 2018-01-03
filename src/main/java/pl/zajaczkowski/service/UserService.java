package pl.zajaczkowski.service;

import pl.zajaczkowski.model.User;

public interface UserService {

	User findById(Long id);
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
	void saveRegistrationUser(User user);
	void saveUser(User user);
	void deleteUser(User user);
}
