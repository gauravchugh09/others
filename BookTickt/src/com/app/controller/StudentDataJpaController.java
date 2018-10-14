package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.Student1;
import com.app.service.StudentDataJpaService;

@RestController
@RequestMapping("/datajpa")
public class StudentDataJpaController {

	@Autowired
	private StudentDataJpaService sdjs;

	@RequestMapping(value = "/getAllStudent", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student1>> getAllStudent() {
		return new ResponseEntity<>(sdjs.getAllStudent(), HttpStatus.OK);
	}

}
