package com.octaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octaspring.dao.RoleInterface;
import com.octaspring.entity.UserPerson;


//Recibir peticiones -> Cargar los recursos -> ... 
@Controller
public class ViewController {

	@Autowired
	private RoleInterface roleInterface;
	
	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("userperson") UserPerson userperson, BindingResult result, Model model) {
		model.addAttribute("roles", roleInterface.findRolesNotAdmin());
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String auth() {
		return "login";
	}
	
}
