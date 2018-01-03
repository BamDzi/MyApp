package pl.zajaczkowski.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long id;
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "*Proszę wprowadzić prawidłowo adres e-mail")
	@NotEmpty(message = "*Proszę wprowadzić adres e-mail")
	private String email;
//	@NotNull
//	@Min(7)
//	@Size(min=2, max=8)
	@Column(name = "password")
	@Length(min = 8, message = "*Twoje hasło musi posiadać conajmniej 8 znaków")
	@NotEmpty(message = "*Proszę wprowadzić swoje hasło")
	@Transient
	private String password;
	@Column(name = "active")
	private boolean active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@Column(name = "confirmation_token")
	private String confirmationToken;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getConfirmationToken() {
		return confirmationToken;
	}
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
}