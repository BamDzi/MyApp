package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.ProductAudit;
import pl.zajaczkowski.model.User;

public interface ProductAuditRepository extends JpaRepository<ProductAudit, Integer>{

	List<ProductAudit> findByUserId(Long userId);
}
