package com.app.excelutil;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Dao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate npjt;

	@Autowired
	public void setJdbcTemplate(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
		this.npjt=new NamedParameterJdbcTemplate(ds);
	}

	private final String dbName = "dev_1013973";

	public Integer getPrimaryKey() {
		String query = "select max(id) from " + dbName + ".incident_details";
		Integer maxId = jdbcTemplate.query(query, new MapInt());
		if (maxId == null) {
			return 0;
		}
		return maxId + 1;
	}

	public Object getUserIdByUserName(Object userName) {
		Integer userId = null;
		try {
			// userName=userName.toString().split("(")[0].trim();
			String user = ((String) userName).split("\\(")[0].trim();

			String query = "select id from " + dbName + ".user_details ud where ud.name=?";
			userId = jdbcTemplate.query(query, new MapInt(), user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userId;
	}

	public Object getMasterAppIdByName(Object appName) {
		String query = "select id from " + dbName + ".master_application ma where ma.name=?";
		Integer appId = jdbcTemplate.query(query, new MapInt(), appName);
		return appId;
	}

	public Object getSlaIdByType(Object slaType) {
		String query = "select id from " + dbName + ".master_sla_type mst where mst.name=?";
		Integer slaId = jdbcTemplate.query(query, new MapInt(), slaType);
		return slaId;
	}

	public Object getTrackIdByName(Object name) {
		String query = "select id from " + dbName + ".master_track mt where mt.name=?";
		Integer trackId = jdbcTemplate.query(query, new MapInt(), name);
		return trackId;
	}

	public Object getStatusIdByName(Object status) {
		String query = "select id from " + dbName + ".master_ticket_status mts where mts.name=?";
		Integer statusId = jdbcTemplate.query(query, new MapInt(), status);
		return statusId;
	}

	public void insert(Map<String, Object> dataMap) {
		String query = "select count(*) from " + dbName + ".incident_details i where i.inc_id=?";
		Integer found = jdbcTemplate.query(query, new MapInt(), dataMap.get("inc_id"));
		if (found != null && found == 1) {
			String query1 = "UPDATE " + dbName + ".incident_details i set i.user_id=? where i.inc_id=?";
			jdbcTemplate.update(query1, dataMap.get("user_id"), dataMap.get("inc_id"));
		} else {
			Set<Entry<String, Object>> entries = dataMap.entrySet();
			String part1 = "";
			String part2 = "";
			for (Entry<String, Object> entry : entries) {
				part1 = part1 + entry.getKey() + ",";
				part2 = part2 + ":"+entry.getKey() + ",";
			}

			part1 = part1.replaceAll(",$", "");
			part2 = part2.replaceAll(",$", "");

			part1 = "(" + part1 + ")";
			part2 = "(" + part2 + ")";

			String query2 = "INSERT INTO " + dbName + ".incident_details " + part1 + " values " + part2;
			System.out.println(query2);
			npjt.update(query2, dataMap);
			//jdbcTemplate.update(query2);
		}
	}
}
