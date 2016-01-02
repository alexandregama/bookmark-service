package com.bookmarks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@RequestMapping(value = "/greetings", method = RequestMethod.GET)
	public String hello(@RequestParam(name = "name", required = false) String name, Model model) {
		model.addAttribute("name", name);

		return "greetings";
	}

}
