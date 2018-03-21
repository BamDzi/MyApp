package pl.zajaczkowski.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {
	
		@Id
		@GeneratedValue
	    private Long id;
	    private String name = null;
	    @CreatedDate
	    private Calendar customerSince = null;
//	    @OneToOne
//	    @Column(name="userId")
//	    private User user;
	    
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			Customer other = (Customer) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		
	    
	    

//		public User getUser() {
//			return user;
//		}
//
//		public void setUser(User user) {
//			this.user = user;
//		}

}
