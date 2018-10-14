package com.app.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.app.bean.Student1;
import com.app.resultsetexc.SportCatResulExct;
import com.app.resultsetexc.StudentListResultExc;
import com.app.resultsetexc.StudentResultSet;
import com.app.rowmapper.StudentRowMapper;

@Component
public class StudenDaoJdbc {

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate npjt;

	@Autowired
	public void setJdbcTemplate22(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
		this.npjt = new NamedParameterJdbcTemplate(ds);
	}

	public void insert() {
		String query = "insert into student values (?,?,?,?,?)";
		jdbcTemplate.update(query, new Object[] { 3, "himanshu", 678, "12th", "carrom" });
	}

	public void insert1() {
		String query = "insert into student values (:id,:name,:roll_no,:standard,:sport)";
		Map<String, Object> paramAP = new HashMap<>();
		paramAP.put("name", "gaurav1");
		paramAP.put("id", 3);
		paramAP.put("sport", "football");
		paramAP.put("roll_no", 876);
		paramAP.put("standard", "7th");
		npjt.update(query, paramAP);

	}

	public Integer getTotalStudent() {
		String query = "select count(*) from student";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	public Student1 getStuden(Integer id) {
		String query = "select * from student where id=:id";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("id", id);
		Student1 s = npjt.queryForObject(query, map, new BeanPropertyRowMapper<>(Student1.class));
		return s;
	}

	public Student1 getStuden1(Integer id) {
		String query = "select * from student where id=?";
		/*
		 * RowMapper<Student1> rw = new RowMapper<Student1>() {
		 * 
		 * @Override public Student1 mapRow(ResultSet rs, int rowNum) throws
		 * SQLException { System.out.println(rowNum); Student1 student = new Student1();
		 * student.setId(rs.getInt("id")); student.setName(rs.getString("name"));
		 * student.setRollNo(rs.getInt("roll_no"));
		 * student.setSport(rs.getString("sport"));
		 * student.setStandard(rs.getString("standard")); return student; }
		 * 
		 * };
		 */
		Object[] o = { id };
		//Map<String, Object> map = jdbcTemplate.queryForMap(query, o);
		// Student1 stu = jdbcTemplate.queryForObject(query, o, new StudentRowMapper());
		Student1 stu = jdbcTemplate.queryForObject(query, o, new BeanPropertyRowMapper<>(Student1.class));
		return stu;
	}

	public List<Student1> getAllStudent() {
		String query = "select * from student";
		List<Map<String, Object>> map = jdbcTemplate.queryForList(query);
		List<Student1> student1s = new ArrayList<>();
		Student1 student = null;
		for (Map<String, Object> m : map) {
			student = new Student1();
			student.setId(((BigDecimal) m.get("id")).intValue());
			student.setName(m.get("name").toString());
			student.setRollNo(((BigDecimal) m.get("roll_no")).intValue());
			student.setSport(m.get("sport").toString());
			student.setStandard(m.get("sport").toString());
			student1s.add(student);
		}
		return student1s;
	}

	public List<Student1> getAllStudent1() {
		String query = "select * from student";
		List<Student1> student1s = jdbcTemplate.query(query, new StudentRowMapper());
		return student1s;
	}

	public Student1 getStudent2(Integer id) {
		String query = "select * from student where id=?";
		return jdbcTemplate.query(query, new StudentResultSet(), id);
	}

	public List<Student1> getAllStudents2() {
		String query = "select * from student";
		return jdbcTemplate.query(query, new StudentListResultExc());
	}

	public Map<String, String> getSportMap() {
		String query = "select * from student";
		return jdbcTemplate.query(query, new SportCatResulExct());
	}

}
