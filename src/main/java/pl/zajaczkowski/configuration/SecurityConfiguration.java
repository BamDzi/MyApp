package pl.zajaczkowski.configuration;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import pl.zajaczkowski.model.User;

@Configuration
//@EnableOAuth2Sso
@EnableOAuth2Client
@EnableWebSecurity
@RestController
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.usersQuery}")
	private String usersQuery;
	
	@Value("${spring.queries.rolesQuery}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}
	
/*	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .antMatcher("/**")
	      .authorizeRequests()
	        .antMatchers("/", "/login**", "/webjars/**")
	        .permitAll()
//	      .anyRequest()
//	        .authenticated()
	        .and().logout().logoutSuccessUrl("/").permitAll()
	        .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	        .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	  }*/

	@Override
	protected void configure(HttpSecurity http) throws Exception { //defines which URL paths should be secured and which should not
		
		http.
			authorizeRequests()
//				.antMatchers("/", "/login", "/registration", "/confirm", "/update").permitAll() //paths are configured to not require any authentication
				.antMatchers("/vendor/**").hasAuthority("VENDOR")	//require admin role
//				.anyRequest().authenticated()//All other paths must be authenticated
				.antMatchers("/cart", "/submit").authenticated()
				.anyRequest().permitAll()
				.and().formLogin().loginPage("/login")	//supported method 'POST' for request /login!!!!!!
     													//When a user successfully logs in, they will be redirected to the previously 
	    												//requested page that required authentication.
//				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/")
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll()
				.and().exceptionHandling().accessDeniedPage("/access-denied")
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		        .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	private Filter ssoFacebookFilter() {
		OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(
				"/login/facebook");
		facebookFilter.setRestTemplate(new OAuth2RestTemplate(facebook(), oauth2ClientContext));
		facebookFilter.setTokenServices(new UserInfoTokenServices(facebookResource().getUserInfoUri(),
				facebook().getClientId()));
		facebookFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler() {
		    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		        this.setDefaultTargetUrl("/");
		        super.onAuthenticationSuccess(request, response, authentication);
		    }
		});
		return facebookFilter;
	}

	@Bean
	@ConfigurationProperties("facebook.client")
	public AuthorizationCodeResourceDetails facebook() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("facebook.resource")
	public ResourceServerProperties facebookResource() {
		return new ResourceServerProperties();
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("google.client")		//("security.oauth2.google.client")
	public AuthorizationCodeResourceDetails google() {
	    return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("google.resource")		//("security.oauth2.google.resource")
	public ResourceServerProperties googleResource() {
	    return new ResourceServerProperties();
	}

	private Filter ssoGoogleFilter() {
	    OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
	    googleFilter.setRestTemplate(new OAuth2RestTemplate(google(), oauth2ClientContext));
	    googleFilter.setTokenServices(new UserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId()));
	    googleFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler() {
		    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		        this.setDefaultTargetUrl("/");
		        super.onAuthenticationSuccess(request, response, authentication);
		    }
		});
	    return googleFilter;
	}
	
	private Filter ssoFilter() {
	     List<Filter> filters = new ArrayList<>();
	     filters.add(ssoGoogleFilter());
	     filters.add(ssoFacebookFilter());
	 
	     CompositeFilter filter = new CompositeFilter();
	     filter.setFilters(filters);
	     return filter;
	}

}
	


