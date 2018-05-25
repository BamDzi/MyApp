package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.VendorAudit;

public interface VendorAuditRepository extends JpaRepository<VendorAudit, Long> {
	
	List<VendorAudit> findByVendorId(Long vendorId);

}
