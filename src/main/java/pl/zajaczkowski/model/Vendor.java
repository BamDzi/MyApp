package pl.zajaczkowski.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vendor {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String nip;
	private String phoneNumber;
	
	/*@OneToOne
	@JoinColumn(name = "addressId")
	private Address address;
	@OneToMany(mappedBy = "seller")
	private List<Product> products;
*/
	
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
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
