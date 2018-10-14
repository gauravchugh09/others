package com.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bean.Student1;
import com.app.dao.StudTransDao;
import com.app.entity.AddressEntity;
import com.app.entity.StudentEntity;

@Service
public class StudTransService {

	@Autowired
	private StudTransDao studTransDao;

	public void insertStudent(Student1 student) {
		StudentEntity studentEntity = new StudentEntity();
		AddressEntity addressEntity = new AddressEntity();

		BeanUtils.copyProperties(student, studentEntity);
		BeanUtils.copyProperties(student.getAddress1(), addressEntity);

		studentEntity.setAddressEntity(addressEntity);

		studTransDao.insertStudent(studentEntity);

	}

}
