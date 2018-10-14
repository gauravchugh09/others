package com.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.bean.Student1;
import com.app.entity.StudentEntity;

@Repository
public class StudTransDao {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public void setTemp(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT, timeout = 100)
	public void insertStudent(Student1 student) throws Exception {
		String query = "insert into student values (?,?,?,?,?)";
		Object[] objs = new Object[] { student.getId(), student.getName(), student.getRollNo(), student.getStandard(),
				student.getSport() };
		jdbcTemplate.update(query, objs);
		if (student.getSport() == null) {
			throw new NullPointerException();
		}
		query = "insert into address values (?,?,?,?,?,?,?)";
		objs = new Object[] { student.getAddress1().getId(), student.getAddress1().getAdrLn1(),
				student.getAddress1().getAdrLn2(), student.getAddress1().getCity(), student.getAddress1().getState(),
				student.getAddress1().getPinCode(), student.getId() };
		jdbcTemplate.update(query, objs);
	}

	@Transactional
	public void insertStudent(StudentEntity studentEntity) {
		entityManager.persist(studentEntity);
		entityManager.persist(studentEntity.getAddressEntity());
	}
}
