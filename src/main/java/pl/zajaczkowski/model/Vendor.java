package pl.zajaczkowski.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Vendor {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private User user;
	private String name;
	private String nip;
	private String phoneNumber;
	private String email;
	private String zipCode;
	private String locality;
	private String street;
	private String streetNumber;
	@Lob
	private String introduction;
//	private String history;

//	@OneToOne
//	@JoinColumn(name = "image_Id")
//	@Transient
//	private Image image;
//	public Vendor() {
//		
//	}

//	public Vendor(Integer id, User user, String name, String nip, String phoneNumber, String email, String zipCode,
//		String locality, String street, String streetNumber, String introduction, String history) {
//	super();
//	this.id = id;
//	this.user = user;
//	this.name = name;
//	this.nip = nip;
//	this.phoneNumber = phoneNumber;
//	this.email = email;
//	this.zipCode = zipCode;
//	this.locality = locality;
//	this.street = street;
//	this.streetNumber = streetNumber;
//	this.introduction = introduction;
//	this.history = history;
//}



	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
