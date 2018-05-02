package pl.zajaczkowski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.zajaczkowski.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findById(Integer id);
	Category findByName(String name);
	List<Category> findAll();
}
