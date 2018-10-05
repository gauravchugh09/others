package com.app.excelutil;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class MapInt implements ResultSetExtractor<Integer> {

	@Override
	public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs.next()) {
			Integer id = rs.getInt(1);
			return id;
		}
		return null;
	}

}
