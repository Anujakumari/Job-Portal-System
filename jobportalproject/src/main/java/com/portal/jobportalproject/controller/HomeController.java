package com.portal.jobportalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	    @GetMapping("/")
	    public String home() {
	        return "home";  // loads templates/home.html
	    }

}
