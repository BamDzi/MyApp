package pl.zajaczkowski.model;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue
	private Long id;
	private Calendar date;
	@OneToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	@OneToMany
	private Set<OrderLine> orderLines = new LinkedHashSet<OrderLine>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}
//	public void setOrderLines(Set<OrderLine> orderLines) {
//		this.orderLines = orderLines;
//	}

}
