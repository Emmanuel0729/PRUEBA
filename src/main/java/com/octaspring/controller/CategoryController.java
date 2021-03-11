package com.octaspring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.entity.Category;

@Controller
public class CategoryController {
	@Autowired
	private CategoryInterface categoryInterface;
	
	@GetMapping("/category")
	public String lang(@ModelAttribute("category") Category category, BindingResult result,  Model modelo) {
		List<Category> listaLang= categoryInterface.findAll();
		modelo.addAttribute("listaTL", listaLang);
		return "admin/category";
	}
	
	@GetMapping("/category-delete/{id}")
	public String categoryDelete(@PathVariable("id") Integer id) {
		categoryInterface.delete(id);
		return "redirect:/category";
	}
	
	@GetMapping("/category-update/{id}")
	public String categoryUpdate(@PathVariable("id") Integer id, @ModelAttribute("category") Category category, BindingResult result,  Model modelo) {
		category = categoryInterface.findById(id);
		List<Category> listaLang= categoryInterface.findAll();
		modelo.addAttribute("listaTL", listaLang);
		modelo.addAttribute("category", category);
		return "admin/category";
	}
	
	@PostMapping("/category-create")
	public String categoryCreate(@Valid Category category, BindingResult result,  @RequestParam("file") CommonsMultipartFile file, HttpSession session, Model modelo) throws IOException {
		if(result.hasErrors()) {
			System.out.println("Errores >>> ....");
			List<Category> listaLang= categoryInterface.findAll();
			modelo.addAttribute("listaTL", listaLang);
			modelo.addAttribute("category", category);
			return "admin/category";
		}
		
		System.out.println("ddd");
		System.out.println(file.getOriginalFilename());
		String path = session.getServletContext().getRealPath("/");
		
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path pathupload = Paths.get(path+"/WEB-INF/uploads/category/"+file.getOriginalFilename());
			Files.write(pathupload, bytes);
			category.setImage(file.getOriginalFilename());
		}else {
			category.setImage("default.png");
		}
		
		System.out.println(path);
		if(category.getId() == null) {
			categoryInterface.save(category);
		}else {
			categoryInterface.update(category);
		}
		
		return "redirect:/category";
	}
}
