package pl.zajaczkowski.model;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "*Proszę wprowadzić prawidłowo adres e-mail")
	@NotEmpty(message = "*Proszę wprowadzić adres e-mail")
	private String email;
	// @NotNull
	// @Min(7)
	// @Size(min=2, max=8)
	@Column(name = "password")
	@Length(min = 8, message = "*Twoje hasło musi posiadać conajmniej 8 znaków")
	@NotEmpty(message = "*Proszę wprowadzić swoje hasło")
	@Transient
	private String password;
	@Column(name = "active")
	private boolean active = false;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@Column(name = "confirmation_token")
	private String confirmationToken;
	@Column(name = "customer_since")
	@CreatedDate
	private Calendar customerSince;
	// @OneToOne
	// @Transient
	// private Customer customer;

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

	public Calendar getCustomerSince() {
		return customerSince;
	}

	public void setCustomerSince(Calendar customerSince) {
		this.customerSince = customerSince;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((confirmationToken == null) ? 0 : confirmationToken.hashCode());
		result = prime * result + ((customerSince == null) ? 0 : customerSince.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (active != other.active)
			return false;
		if (confirmationToken == null) {
			if (other.confirmationToken != null)
				return false;
		} else if (!confirmationToken.equals(other.confirmationToken))
			return false;
		if (customerSince == null) {
			if (other.customerSince != null)
				return false;
		} else if (!customerSince.equals(other.customerSince))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}

}
