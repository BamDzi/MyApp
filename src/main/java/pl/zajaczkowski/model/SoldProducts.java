package pl.zajaczkowski.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SoldProducts {
	
	@Id
	@GeneratedValue
	private Long id;
//	private Product productId;
	private LocalDate month;
	private double soldQuantity;		//Sprzedana ilość
	private BigDecimal total;			//Kwota
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Product getProductId() {
//		return productId;
//	}
//	public void setProductId(Product productId) {
//		this.productId = productId;
//	}
	public LocalDate getMonth() {
		return month;
	}
	public void setMonth(LocalDate month) {
		this.month = month;
	}
	public double getSoldQuantity() {
		return soldQuantity;
	}
	public void setSoldQuantity(double soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
