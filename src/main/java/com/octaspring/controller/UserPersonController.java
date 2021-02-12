package com.octaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.UserPerson;
import com.octaspring.service.UserPersonService;

@Controller
public class UserPersonController {
	
	@Autowired
	private UserPersonInterface userPersonInterface;
	
	@PostMapping("/user-create")
	public String userPersonCreate(UserPerson userperson) {
		System.out.println(userperson.toString());
		userPersonInterface.save(userperson);
		return "redirect:/register";
	}
}
