package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.Student1;
import com.app.service.StudentServiceHibernate;

@RestController
@RequestMapping(value = "/studorm")
public class StudentControllerHibernate {

	@Autowired
	private StudentServiceHibernate ss;

	@RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> insertEmployee(@RequestBody Student1 student) {
		ss.insertStudent(student);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/getAllStudent", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student1>> getAllStudent() {
		return new ResponseEntity<>(ss.getAllStudent(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getStudent/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student1> getStudentById(@PathVariable("id") Integer id) {
		Student1 student1 = ss.getStudentById(id);
		return new ResponseEntity<>(student1, HttpStatus.OK);
	}

	@RequestMapping(value = "/delStu/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer id) {
		ss.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
