package com.app.resultsetexc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.app.bean.Student1;

public class StudentListResultExc implements ResultSetExtractor<List<Student1>> {

	@Override
	public List<Student1> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Student1> student1s = new ArrayList<>();
		Student1 student1 = null;
		while (rs.next()) {
			student1 = new Student1();
			student1.setId(rs.getInt("id"));
			student1.setName(rs.getString("name"));
			student1.setRollNo(rs.getInt("roll_no"));
			student1.setSport(rs.getString("sport"));
			student1.setStandard(rs.getString("standard"));
			student1s.add(student1);
		}
		return student1s;
	}

}
