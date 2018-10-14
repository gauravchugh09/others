package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.Student1;
import com.app.dao.StudTransDao;
import com.app.service.StudTransService;

@RestController
@RequestMapping(value = "/studtrans")
public class StudentTransactionController {

	@Autowired
	private StudTransDao studAddJdbcDao;

	@Autowired
	private StudTransService sts;

	@RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> insertEmployee(@RequestBody Student1 student) throws Exception {
		studAddJdbcDao.insertStudent(student);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/insert1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> insertEmployee1(@RequestBody Student1 student) throws Exception {
		sts.insertStudent(student);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

}
