package com.octaspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.octaspring.entity.UserPerson;


//Recibir peticiones -> Cargar los recursos -> ... 
@Controller
public class ViewController {


	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("userperson") UserPerson userperson, BindingResult result, Model model) {
		
		return "register";
	}
	
}
