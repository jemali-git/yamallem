package com.atn.demo.mvc.module.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atn.demo.mvc.module.user.entity.User;
import com.atn.demo.mvc.module.user.service.UserService;

@Controller
public class UserController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView redirectPage(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/home/index");
		return model;
	}
	@RequestMapping(value = { "/home/login" }, method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("home/login");
		return model;
	}

	@RequestMapping(value = { "/403**" }, method = RequestMethod.GET)
	public ModelAndView err403() {
		ModelAndView model = new ModelAndView();
		model.setViewName("403");
		return model;
	}
}
