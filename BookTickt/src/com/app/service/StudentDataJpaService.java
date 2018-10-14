package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.bean.Address1;
import com.app.bean.Student1;
import com.app.entity.AddressEntity;
import com.app.entity.StudentEntity;
import com.app.repo.StudentRepo;

@Service
public class StudentDataJpaService {

	@Autowired
	private StudentRepo studentRepo;

	public List<Student1> getAllStudent() {
		List<StudentEntity> studentEntities = studentRepo.findAll();
		List<Student1> students = new ArrayList<>();
		Student1 student = null;
		Address1 address = null;
		for (StudentEntity se : studentEntities) {
			student = new Student1();
			address = new Address1();
			BeanUtils.copyProperties(se, student);
			AddressEntity ae = se.getAddressEntity();
			BeanUtils.copyProperties(ae, address);
			student.setAddress1(address);
			students.add(student);
		}

		return students;
	}

	public Student1 getStudent(Integer id) {
		StudentEntity se = studentRepo.getOne(id);
		Student1 student = new Student1();
		BeanUtils.copyProperties(se, student);
		Address1 address = new Address1();
		BeanUtils.copyProperties(se.getAddressEntity(), address);
		student.setAddress1(address);
		return student;

	}

}
