package pl.zajaczkowski.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}


	public List<Category> findAllCategorys() {
		return categoryRepository.findAll();
	}
}