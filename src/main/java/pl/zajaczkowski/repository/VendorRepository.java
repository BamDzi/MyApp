package pl.zajaczkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.User;
import pl.zajaczkowski.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{

	Vendor findVendorByUser(User user);
}
