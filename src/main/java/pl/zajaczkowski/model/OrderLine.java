package pl.zajaczkowski.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Product product;
    private Integer amount;
    private BigDecimal purchasePrice;
    
    
//    public OrderLine() {
//        super();
//    }
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
    public Product getProduct() {
        return this.product;
    }
	public void setProduct(final Product product) {
        this.product = product;
    }
    

    public Integer getAmount() {
        return this.amount;
    }
    public void setAmount(final Integer amount) {
        this.amount = amount;
    }


    public BigDecimal getPurchasePrice() {
        return this.purchasePrice;
    }
    public void setPurchasePrice(final BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
}
