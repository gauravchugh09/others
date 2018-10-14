package com.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.entity.StudentEntity;

@Repository
public class StudentDaoHibernate {

	@Autowired
	private SessionFactory sessionFactory;

	public void insertStudent(StudentEntity se) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(se);
		transaction.commit();
		session.close();
	}

	public List<StudentEntity> getAllStudent() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String sql = "select s from StudentEntity s";
		Query query = session.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<StudentEntity> studentEntities = query.list();
		transaction.commit();
		session.close();
		return studentEntities;
	}

	public StudentEntity getStudentById(Integer id) {
		Session session = sessionFactory.openSession();
		StudentEntity se = session.get(StudentEntity.class, id);
		session.close();
		return se;
	}

	public void deleteStudent(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		StudentEntity se = session.get(StudentEntity.class, id);
		session.delete(se);
		transaction.commit();
		session.close();
	}
}
