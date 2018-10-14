package com.app.resultsetexc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class SportCatResulExct implements ResultSetExtractor<Map<String, String>> {

	@Override
	public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, String> sportMap = new HashMap<>();
		while (rs.next()) {
			sportMap.put(rs.getString("sport"), rs.getString("name"));
		}
		return sportMap;
	}

}
