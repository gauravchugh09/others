package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bean.Student1;
import com.app.dao.StudentDaoHibernate;
import com.app.entity.StudentEntity;

@Service
public class StudentServiceHibernate {

	@Autowired
	private StudentDaoHibernate studentDaoHibernate;

	public void insertStudent(Student1 student) {
		StudentEntity se = new StudentEntity();
		BeanUtils.copyProperties(student, se);
		studentDaoHibernate.insertStudent(se);
	}

	public List<Student1> getAllStudent() {
		List<StudentEntity> studentEntities = studentDaoHibernate.getAllStudent();
		List<Student1> students = new ArrayList<>();
		Student1 student1 = null;
		for (StudentEntity se : studentEntities) {
			student1 = new Student1();
			BeanUtils.copyProperties(se, student1);
			students.add(student1);
		}
		return students;
	}
	
	public Student1 getStudentById(Integer id)
	{
		StudentEntity se=studentDaoHibernate.getStudentById(id);
		Student1 student=new Student1();
		BeanUtils.copyProperties(se,student);
		return student;
	}
	
	public void deleteStudent(Integer id)
	{
		studentDaoHibernate.deleteStudent(id);
	}

}
