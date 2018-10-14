package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.entity.StudentEntity;

@Repository
public class StudentDaoJpa {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public void insertStudent(StudentEntity se) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(se);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<StudentEntity> getAllStudent() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String sql = "select s from StudentEntity s";
		Query query = entityManager.createQuery(sql);
		List<StudentEntity> studentEntities = query.getResultList();
		return studentEntities;
	}

	public StudentEntity getStudentById(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(StudentEntity.class, id);
	}

	public void deleteStudent(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
		entityManager.remove(studentEntity);
		transaction.commit();
	}

}
