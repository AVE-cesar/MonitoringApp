package com.caceis.monitor.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/config")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "config";
	}
}
