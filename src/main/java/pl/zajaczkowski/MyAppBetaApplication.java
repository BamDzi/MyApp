package pl.zajaczkowski;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import pl.zajaczkowski.configuration.AuditorAwareImpl;

@EnableJpaAuditing
@SpringBootApplication
public class MyAppBetaApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MyAppBetaApplication.class, args);
	}
	
	  @Bean
	    public AuditorAware<String> auditorAware() {
	        return new AuditorAwareImpl();
	    }
}
