package com.home.springmybatis;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	final static Logger LOGGER = Logger.getLogger(HelloController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		LOGGER.debug(" Logger: " + HelloController.class);

		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}