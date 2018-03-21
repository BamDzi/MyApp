package pl.zajaczkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

	OrderLine findById(Long id);
}
