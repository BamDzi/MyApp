package pl.zajaczkowski.service;

import pl.zajaczkowski.model.User;

public interface UserService {

	public User findUserByEmail(String email);
	public void saveUser(User user);
}
