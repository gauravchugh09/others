package com.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.bean.Student1;

public class StudentRowMapper implements RowMapper<Student1> {

	@Override
	public Student1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student1 student = new Student1();
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setRollNo(rs.getInt("roll_no"));
		student.setSport(rs.getString("sport"));
		student.setStandard(rs.getString("standard"));
		return student;
	}

}
