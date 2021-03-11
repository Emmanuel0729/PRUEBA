package com.octaspring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.entity.Subcategory;

@Controller
public class SubcategoryController {
	@Autowired
	private SubcategoryInterface subcategoryInterface;
	@Autowired
	private CategoryInterface categoryInterface;
	
	@GetMapping("/subcategory")
	public String subcategory(@ModelAttribute("subcategory") Subcategory subcategory, BindingResult result,  Model modelo) {
		List<Subcategory> listaLang= subcategoryInterface.findAll();
		modelo.addAttribute("listaTL", listaLang);
		modelo.addAttribute("categorias", categoryInterface.findAll());
		return "admin/subcategory";
	}
	
	@PostMapping("/subcategory-create")
	public String subcategoryCreate(Subcategory subcategory, Model modelo, @ModelAttribute("selectCategor") int category) throws IOException {
		if(subcategory.getId() == null) {
			//subcategory.setId(new Long(1));
			subcategoryInterface.save(subcategory, category);
		}else {
			subcategoryInterface.update(subcategory, category);
		}
		return "redirect:/subcategory";
	}
	
	@GetMapping("/subcategory-delete/{id}")
	public String categoryDelete(@PathVariable("id") Integer id) {
		subcategoryInterface.delete(id);
		return "redirect:/subcategory";
	}
	
	@GetMapping("/subcategory-update/{id}")
	public String subcategoryUpdate(@PathVariable("id") Integer id, @ModelAttribute("subcategory") Subcategory subcategory, BindingResult result,  Model modelo) {
		subcategory = subcategoryInterface.findById(id);
		List<Subcategory> listaLang= subcategoryInterface.findAll();
		modelo.addAttribute("listaTL", listaLang);
		modelo.addAttribute("categorias", categoryInterface.findAll());
		modelo.addAttribute("subcategory", subcategory);
		return "admin/subcategory";
	}
}
