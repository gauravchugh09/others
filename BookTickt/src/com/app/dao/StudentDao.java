package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.app.bean.Student;

@Component
public class StudentDao {

	private Connection connection = null;
	private PreparedStatement ps = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "DEVloper";
	private String password = "dev12345";

	public void insert(Student student) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
			String query = "insert into student values (?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setString(2, "gaurav");
			ps.setInt(3, 751);
			ps.setString(4, "10th");
			ps.setString(5, "table tennis");

			ps.executeQuery();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
