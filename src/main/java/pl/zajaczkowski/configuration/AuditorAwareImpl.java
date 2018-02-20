package pl.zajaczkowski.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import pl.zajaczkowski.model.User;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		// return "nowyTestowy";
		// return
		// ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
		// NIE DZIAŁA
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;

		}
		return "nieUdany";
	}

}
