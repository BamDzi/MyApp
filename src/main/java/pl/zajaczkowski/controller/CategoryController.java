package pl.zajaczkowski.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.zajaczkowski.model.Category;
import pl.zajaczkowski.repository.CategoryRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private List<Category> categorys;
	
//	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}



	@PostMapping("createCategory")
	public String createCategory() {
		
		this.categorys = new ArrayList<Category>();
		
		final Category cate1 = new Category("Mięso");
		final Category cate2 = new Category("Nabiał");
		final Category cate3 = new Category("Ryby");
		final Category cate4 = new Category("Wędliny");
		
		this.categorys.add(cate1);
		this.categorys.add(cate2);
		this.categorys.add(cate3);
		this.categorys.add(cate4);
		
		categoryRepository.save(categorys);
		
		return "redirect:/vendor";
	}
}
