package pl.zajaczkowski.model;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Orders {

	@Id
	@GeneratedValue
	private Long id = null;
	@CreatedDate
	private Calendar date = null;
	@OneToOne
	private Customer customer = null;
	@OneToMany
	@JoinColumn(name = "order_Id")
	private Set<OrderLine> orderLines;// = new LinkedHashSet<OrderLine>();

	public Orders() {
	}

	public Orders(Customer customer) {
		super();
		this.customer = customer;
	}

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

	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

}
