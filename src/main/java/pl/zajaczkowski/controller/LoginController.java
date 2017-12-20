package pl.zajaczkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping
	public String loginPage() {
		return "default";
	}

	@GetMapping("meat")
	public String meat() {
		return "meat";
	}
	
	@GetMapping("basket")
	public String basket() {
		return "basket";
	}
	
	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("detail")
	public String detail() {
		return "detail";
	}
	
	@GetMapping("onli")
	public String onli() {
		return "online";
	}
	
	@GetMapping("registration")
	public String registration() {
		return "registration";
	}
	
	@GetMapping("settings")
	public String settings() {
		return "settings";
	}
	
	@GetMapping("submit")
	public String submit() {
		return "submit";
	}
}
