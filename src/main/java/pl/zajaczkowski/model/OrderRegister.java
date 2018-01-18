package pl.zajaczkowski.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderRegister {
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private String listOfProduct;			//może lista albo zbiur albo mapa
	private BigDecimal cost;
	private LocalDate date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getListOfProduct() {
		return listOfProduct;
	}
	public void setListOfProduct(String listOfProduct) {
		this.listOfProduct = listOfProduct;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
