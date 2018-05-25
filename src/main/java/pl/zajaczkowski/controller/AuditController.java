package pl.zajaczkowski.controller;

import org.springframework.stereotype.Controller;

import pl.zajaczkowski.repository.ProductAuditRepository;
import pl.zajaczkowski.repository.VendorAuditRepository;

@Controller
public class AuditController {

	private ProductAuditRepository productAuditRepository;
	private VendorAuditRepository vendorAuditRepository;
	
	public AuditController(ProductAuditRepository productAuditRepository, VendorAuditRepository vendorAuditRepository) {
		super();
		this.productAuditRepository = productAuditRepository;
		this.vendorAuditRepository = vendorAuditRepository;
	}
	

	
	
}
