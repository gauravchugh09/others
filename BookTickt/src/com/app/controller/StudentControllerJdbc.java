package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.Student1;
import com.app.dao.StudenDaoJdbc;

@RestController
@RequestMapping("/student")
public class StudentControllerJdbc {

	@Autowired
	private StudenDaoJdbc stuSpr;

	@RequestMapping(value = "/insertStu", method = RequestMethod.POST)
	public ResponseEntity<Void> insertStudent() {

		stuSpr.insert1();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/getTotalStudent", method = RequestMethod.GET)
	public Integer getTotalStudent() {
		return stuSpr.getTotalStudent();
	}

	@RequestMapping(value = "/getStudent/{id}", method = RequestMethod.GET)
	public Student1 getStudent(@PathVariable("id") Integer id) {
		return stuSpr.getStudent2(id);
	}

	@RequestMapping(value = "/getAllStudent", method = RequestMethod.GET)
	public List<Student1> getAllStdent() {
		return stuSpr.getAllStudents2();
	}
	
	@RequestMapping(value = "/getSportMap", method = RequestMethod.GET)
	public Map<String,String> getSportMap()
	{
		return stuSpr.getSportMap();
	}
}
