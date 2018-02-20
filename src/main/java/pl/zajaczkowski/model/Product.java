package pl.zajaczkowski.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private BigDecimal price; // cena zakupu
	// private double amount; //kwota
	private double quantity;
	private boolean inStock;
	private String description;
	private String components;
	private String tips;
//	private String category;
//	@OneToOne
//	@JoinColumn(name = "user_id")
//	@CreatedBy
//	private User user;
	@CreatedBy
	private String user;
	@CreatedDate
	private Calendar createDate;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "vendor_Id") private Vendor vendor;
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "image_Id") private Image image;
	 */

	public Calendar getCreateDate() {
		return createDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

//	 public User getUser() {
//	 return user;
//	 }
//	 public void setUser(User user) {
//	 this.user = user;
//	 }
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public String getComponents() {
		return components;
	}

	public void setComponents(String components) {
		this.components = components;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
