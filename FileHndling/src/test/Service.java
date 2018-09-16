package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class Service {

	private String path1 = "F:\\test.properties";
	private String path2 = "F:\\countRegister.properties";

	public void core(Map<String, String> map) {
		Map<String, String> oldMap = this.readFile(path1);
		Map<String, String> newMap = map;
		Map<String, String> finalMap = this.combineMap(newMap, oldMap);
		Set<Entry<String, String>> newEntries = newMap.entrySet();

		Map<String, String> oldCounterMap = this.readFile(path2);
		Map<String, String> newCounterMap = this.setDefaultValueCounter(finalMap, oldCounterMap);

		for (Entry<String, String> neEntry : newEntries) {
			Integer newVal = Integer.parseInt(neEntry.getValue());
			Integer oldVal = null;
			if (oldMap.get(neEntry.getKey()) != null) {
				oldVal = Integer.parseInt(oldMap.get(neEntry.getKey()));
			}

			if (oldVal != null) {
				if (newVal > oldVal) {
					Integer counter = Integer.parseInt(newCounterMap.get(neEntry.getKey()));
					counter++;
					newCounterMap.put(neEntry.getKey(), counter.toString());
				} else if (oldVal > newVal) {
					newCounterMap.put(neEntry.getKey(), "0");
				}
			}
		}
		this.writeFile(path1, finalMap);
		this.writeFile(path2, newCounterMap);
	}

	public Map<String, String> setDefaultValueCounter(Map<String, String> map, Map<String, String> counterMap) {
		Set<Entry<String, String>> entries = map.entrySet();
		boolean flag = false;
		for (Entry<String, String> entry : entries) {
			if (counterMap.get(entry.getKey()) == null) {
				counterMap.put(entry.getKey(), "0");
				flag = true;
			}
		}
		if (flag) {
			this.writeFile(path2, counterMap);
		}
		return this.readFile(path2);
	}

	public Map<String, String> combineMap(Map<String, String> newVal, Map<String, String> oldVal) {
		Map<String, String> finalMap = new HashMap<>();
		finalMap.putAll(oldVal);
		finalMap.putAll(newVal);
		return finalMap;
	}

	public void writeFile(String path, Map<String, String> dataMap) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream(path);
			Set<Entry<String, String>> entries = dataMap.entrySet();
			for (Entry<String, String> entry : entries) {
				prop.setProperty(entry.getKey(), entry.getValue());

			}
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public Map<String, String> readFile(String path) {
		Properties prop = new Properties();
		InputStream input = null;
		Map<String, String> dataMap = new HashMap<>();
		try {
			input = new FileInputStream(path);
			prop.load(input);
			Set<String> props = prop.stringPropertyNames();
			for (String s : props) {
				dataMap.put(s, prop.getProperty(s));
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dataMap;

	}

}
