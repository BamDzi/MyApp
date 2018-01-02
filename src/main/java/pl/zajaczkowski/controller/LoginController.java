package pl.zajaczkowski.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.UserRepository;
import pl.zajaczkowski.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String login() {
		return "login";
	}
	
	@GetMapping("registration")
	public String registration(@ModelAttribute User user) {
		return "registration";
	}
	
	@PostMapping("registration")
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {

		User userExists = userService.findUserByEmail(user.getEmail());
		
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		
			userService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());

		
		return "confirm";
	}

	@GetMapping("update")
	public String updatePersonForm(@ModelAttribute User user, @RequestParam String email) {

		user = userService.findUserByEmail(email);
		user.setActive(true);
		userRepository.save(user);
		
		return "update";
	}

	@GetMapping("/admin/online")
	public String home() {
		return "admin/online";
	}
	
	@GetMapping("confirm")
	public String helloPage() {
		return "confirm";
	}
	
	@GetMapping("detail")
	public String detail() {
		return "detail";
	}
	
	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
}
