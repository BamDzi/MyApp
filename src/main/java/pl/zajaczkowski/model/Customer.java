package pl.zajaczkowski.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {
	
		@Id
		@GeneratedValue
	    private Long id;
	    private String name;
	    @CreatedDate
	    private Calendar customerSince;
	    @Transient				//adnotacja sprawia że pole nie zapisuje się w bazie danych
	    private int phoneNumber;
	    @OneToOne
//	    @Column(name="userId")
	    private User user;
	    
//	    public Customer() {
//	        super();
//	    }

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
	        return this.name;
	    }
	    public void setName(final String name) {
	        this.name = name;
	    }

	    public Calendar getCustomerSince() {
	        return this.customerSince;
	    }
	    public void setCustomerSince(final Calendar customerSince) {
	        this.customerSince = customerSince;
	    }
		public int getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(int phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		
}
