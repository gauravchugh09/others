package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.bean.Student;
import com.app.customeditor.StudentEditor;

@Controller
public class LoginController {

	@InitBinder
	public void initbinder(WebDataBinder webDataBinder) {
		String[] disAllowFields = { "mobNo" };
		webDataBinder.setDisallowedFields(disAllowFields);
		SimpleDateFormat sdf = new SimpleDateFormat("dd--MM--yyyy");
		CustomDateEditor cde = new CustomDateEditor(sdf, false);
		webDataBinder.registerCustomEditor(Date.class, "dob", cde);
		StudentEditor se = new StudentEditor();
		webDataBinder.registerCustomEditor(String.class, "userName", se);
	}

	@RequestMapping("/")
	public ModelAndView getLogin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		System.out.println("login called");
		return mv;
	}
	
	
	@RequestMapping("/setPathVar1/{userName}/{passWord}")
	public ModelAndView setPathVaribles1(@PathVariable("userName") String userName,
			@PathVariable("passWord") String passWord) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");
		mv.addObject("userName", userName);
		mv.addObject("passWord", passWord);
		return mv;
	}

	@RequestMapping("/setPathVar2/{userName}/{passWord}")
	public ModelAndView setPathVaribles2(@PathVariable Map<String, String> pathVarMap) {
		String userName = pathVarMap.get("userName");
		String pass = pathVarMap.get("passWord");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");
		mv.addObject("userName", userName);
		mv.addObject("passWord", pass);
		return mv;
	}

	@RequestMapping(value = "/setRqstPrm1", method = RequestMethod.GET)
	public ModelAndView setRequestParam1(@RequestParam("userName") String userName,
			@RequestParam("passWord") String passWord) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");
		mv.addObject("userName", userName);
		mv.addObject("passWord", passWord);
		return mv;
	}

	@RequestMapping(value = "/setRqstPrm2", method = RequestMethod.POST)
	public ModelAndView setRequestParam1(@RequestParam Map<String, String> rqParamMap) {
		String userName = rqParamMap.get("userName");
		String passWord = rqParamMap.get("passWord");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");
		mv.addObject("userName", userName);
		mv.addObject("passWord", passWord);
		return mv;
	}

	@RequestMapping(value = "/setModelAttr", method = RequestMethod.POST)
	public ModelAndView setModelAttr(@Valid @ModelAttribute("student1") Student stu, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if(stu.getPassWord()==null || stu.getPassWord().isEmpty())
		{
			throw new NullPointerException("null pointer exception passssssssss");
		}
		if (result.hasErrors()) {
			mv.setViewName("login");
			mv.addObject("error", result.getFieldError().getDefaultMessage());
			return mv;

		}

		mv.setViewName("display2");
		return mv;
	}

	@ModelAttribute
	public void addCommonObject(Model model) {
		model.addAttribute("msg", "common message");
	}
	
	/*@ExceptionHandler(value=NullPointerException.class)
	public String nullPointerHandler(Exception e)
	{
		System.out.println(e.getMessage());
		return "nullPointer";
	}
	
	@ExceptionHandler(value=IOException.class)
	public String ioHnadler(Exception e)
	{
		System.out.println(e.getMessage());
		return "io";
	}
	
	@ExceptionHandler(value=Exception.class)
	public String genericExceptioHandler(Exception e)
	{
		System.out.println(e.getMessage());
		return "exception";
	}*/
}
