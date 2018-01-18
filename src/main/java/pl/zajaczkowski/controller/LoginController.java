package pl.zajaczkowski.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.zajaczkowski.model.User;
import pl.zajaczkowski.repository.UserRepository;
import pl.zajaczkowski.service.EmailService;
import pl.zajaczkowski.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserRepository userRepository;
	
	/*@Autowired
	public LoginController(UserService userService, EmailService emailService, UserRepository userRepository) {
		this.userService = userService;
		this.emailService = emailService;
		this.userRepository = userRepository;
	}*/

	@GetMapping
	public String login() {
		return "login";
	}
	
	@GetMapping("registration")
	public String registration(@ModelAttribute User user) {
		return "registration";
	}
	
	@PostMapping("registration")
	public String createNewUser(@Valid User user, BindingResult bindingResult, HttpServletRequest request, Model model) {

		User userExists = userService.findByEmail(user.getEmail());
		
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		
			userService.saveRegistrationUser(user);
			
			String appUrl = request.getScheme() + "://" + request.getServerName();

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n" + appUrl
					+ ":8080/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("programowanie11@gmail.com");

			emailService.sendEmail(registrationEmail);

			model.addAttribute("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());

			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());

		
		return "confirm";
	}
	
	@GetMapping("confirm")
	public String showConfirmationPage(User user, Model model, @RequestParam String token) {

		user = userService.findByConfirmationToken(token);

		if (user == null) { // No token found in DB
			return "home";
		}
		user.setActive(true);
		userService.saveUser(user);
		model.addAttribute("confirmationToken", user.getConfirmationToken());

		return "confirm";
	}

	@GetMapping("update")
	public String updatePersonForm(@ModelAttribute User user, @RequestParam String email) {

		user = userService.findByEmail(email);
		user.setActive(true);
		userRepository.save(user);
		
		return "update";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable Long id, User user) {
		user = userService.findById(id);
		userService.deleteUser(user);
		return "redirect:/register";
	}

//	@GetMapping("/vendor")
//	public String home() {
//		return "vendor/vendorpage";
//	}
	
	@GetMapping("/access-denied")
	public String denied() {
		return "denied";
	}
	
	@GetMapping("online")
	public String helloPage() {
		return "online";
	}
	
	@GetMapping("detail")
	public String detail() {
		return "detail";
	}
	
	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("meat")
	public String meat() {
		return "meat";
	}
	
	@GetMapping("basket")
	public String basket() {
		return "basket";
	}
	
	@GetMapping("submit")
	public String submit() {
		return "submit";
	}
}
