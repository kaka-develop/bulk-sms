package com.minhthanh.bulk.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minhthanh.bulk.admin.manager.PathManager;
@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@GetMapping("/404")
	public String notFound() {
		return PathManager._404_VIEW;
	}

}
