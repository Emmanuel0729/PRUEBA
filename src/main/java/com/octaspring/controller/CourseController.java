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
import com.octaspring.dao.CourseInterface;
import com.octaspring.dao.LangInterface;
import com.octaspring.dao.LevelInterface;
import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.entity.Course;

@Controller
public class CourseController {
	@Autowired
	private CourseInterface courseInterface;
	@Autowired
	private SubcategoryInterface subcategoryInterface;
	@Autowired
	private CategoryInterface categoryInterface;
	@Autowired
	private LangInterface langInterface;
	@Autowired
	private LevelInterface levelInterface;
	
	@GetMapping("/course")
	public String course(@ModelAttribute("course") Course course, BindingResult result,  Model model) {
		List<Course> listaLang= courseInterface.findAll();
		model.addAttribute("lista", listaLang);
		model.addAttribute("categorias", categoryInterface.findAll());
		model.addAttribute("subcategorias", subcategoryInterface.findAll());
		model.addAttribute("lenguajes", langInterface.findAll());
		model.addAttribute("levels", levelInterface.findAll());
		return "user/course";
	}
	
	@PostMapping("/course-create")
	public String courseCreate(@Valid Course course, BindingResult result,  @RequestParam("file") CommonsMultipartFile file, HttpSession session, Model modelo, @ModelAttribute("selectCategor") int category, @ModelAttribute("selectSubcategor") int subcategory, @ModelAttribute("selectLang") int lang, @ModelAttribute("selectLevel") int level) throws IOException {
		if(result.hasErrors()) {
			List<Course> listaLang= courseInterface.findAll();
			modelo.addAttribute("lista", listaLang);
			modelo.addAttribute("categorias", categoryInterface.findAll());
			modelo.addAttribute("subcategorias", subcategoryInterface.findAll());
			modelo.addAttribute("lenguajes", langInterface.findAll());
			modelo.addAttribute("levels", levelInterface.findAll());
			modelo.addAttribute("course", course);
			return "user/course";
		}
		System.out.println(file.getOriginalFilename());
		String path = session.getServletContext().getRealPath("/");
		
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path pathupload = Paths.get(path+"/WEB-INF/uploads/category/"+file.getOriginalFilename());
			Files.write(pathupload, bytes);
			course.setImage(file.getOriginalFilename());
		}else {
			course.setImage("default.png");
		}
		
		System.out.println(path);
		System.out.println("ID: "+course.getId());
		if(course.getId() == null) {
			courseInterface.save(course, category, subcategory, level, lang);
		}else {
			courseInterface.update(course, category, subcategory, level, lang);
		}
		return "redirect:/course";
	}
	
	@GetMapping("/course-update/{id}")
	public String courseUpdate(@PathVariable("id") Integer id, @ModelAttribute("course") Course course, BindingResult result,  Model model) {
		course = courseInterface.findById(id);
		System.out.println("ID: "+course.getId());
		List<Course> listaLang= courseInterface.findAll();
		model.addAttribute("lista", listaLang);
		model.addAttribute("categorias", categoryInterface.findAll());
		model.addAttribute("subcategorias", subcategoryInterface.findAll());
		model.addAttribute("lenguajes", langInterface.findAll());
		model.addAttribute("levels", levelInterface.findAll());
		model.addAttribute("course", course);
		return "user/course";
	}
	
	@GetMapping("/course-delete/{id}")
	public String courseDelete(@PathVariable("id") Integer id) {
		courseInterface.delete(id);
		return "redirect:/course";
	}
	
	@GetMapping("/course-deleteLogical/{id}")
	public String courseDeleteLogical(@PathVariable("id") Integer id) {
		Course c = courseInterface.findById(id);
		if(c.getStatus() == 0) {
			courseInterface.deleteLogical(id, 1);
		}else {
			courseInterface.deleteLogical(id, 0);
		}
		return "redirect:/course";
	}
}
