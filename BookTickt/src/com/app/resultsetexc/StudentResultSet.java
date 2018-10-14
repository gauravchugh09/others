package com.app.resultsetexc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.app.bean.Student1;

public class StudentResultSet implements ResultSetExtractor<Student1> {

	@Override
	public Student1 extractData(ResultSet rs) throws SQLException, DataAccessException {
		rs.next();
		Student1 student = new Student1();
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setRollNo(rs.getInt("roll_no"));
		student.setSport(rs.getString("sport"));
		student.setStandard(rs.getString("standard"));
		return student;
	}

}
