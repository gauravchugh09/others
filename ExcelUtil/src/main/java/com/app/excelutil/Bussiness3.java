package com.app.excelutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bussiness3 {

	@Autowired
	private Dao dao;

	private List<String> errorLog = null;

	public List<String> processData(String mainPath, String configPath) throws Exception {

		errorLog = new ArrayList<>();
		// read file
		try {
			Sheet configSheet = this.readFile(configPath);
			Sheet mainSheet = this.readFile(mainPath);

			// set headers maps && set data
			Map<String, Integer> configHeaderMap = this.setHeaderMap(configSheet);
			List<Object[]> configData = this.setData(configSheet, configHeaderMap.size());

			Map<String, Integer> mainHeaderMap = this.setHeaderMap(mainSheet);
			List<Object[]> mainData = this.setData(mainSheet, mainHeaderMap.size());

			Map<Integer, ConfigObj> IndexToBeInserted = this.setUsefulIndexMap(mainHeaderMap, configHeaderMap,
					configData);

			this.uploadData(mainData, IndexToBeInserted);
			return errorLog;

		} catch (Exception e) {
			System.out.println("some error occured while processing data.check error logs");
			System.out.println(e.getMessage());
			errorLog.add(e.getMessage());
			return errorLog;
		}
	}

	private void uploadData(List<Object[]> mainData, Map<Integer, ConfigObj> mainMap) {
		try {
			Integer rowNum = 1;
			Map<String, Object> dataMap = new HashMap<>();
			Integer mainColIndex = 0;
			for (Object[] mainObjs : mainData) {
				rowNum++;
				try {
					dataMap = new HashMap<>();
					dataMap.put("id", dao.getPrimaryKey());
					mainColIndex = 0;
					ConfigObj configObj = null;
					for (Object mainObj : mainObjs) {
						String nullable = null;
						String dependent = null;
						String dependFunc = null;
						String dbColName = null;
						String mainColName = null;
						String expMessage = null;
						configObj = mainMap.get(mainColIndex);
						if (configObj != null) {
							nullable = configObj.getNullable();
							dependent = configObj.getDependent();
							dependFunc = configObj.getDependFunc();
							dbColName = configObj.getDbColName();
							mainColName = configObj.getMainColNane();
							expMessage = mainColName + " cannot be null for row number " + rowNum;
							this.checkNullablException(mainObj, nullable, expMessage);
							if (dependent != null && !dependent.isEmpty() && dependent.toLowerCase().equals("yes")) {
								if (dependFunc != null && !dependFunc.isEmpty()) {
									expMessage = "dependency not found for " + mainColName + " on row number " + rowNum;
									mainObj = this.callDependentFunction(mainObj, dependFunc);

								}
							}
							this.checkNullablException(mainObj, nullable, expMessage);
							dataMap.put(dbColName, mainObj);
						}

						mainColIndex++;
					}

					dao.insert(dataMap);
				} catch (CellValException e) {
					System.out.println(e.getMessage());
					errorLog.add(e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.println("some unknown error occured while uploading data.check error logs");
			System.out.println(e.getMessage());
			errorLog.add(e.getMessage());
		}
	}

	private void checkNullablException(Object mainObj, String nullable, String message) throws CellValException {
		if (nullable != null && !nullable.isEmpty() && nullable.toLowerCase().equals("no")) {
			if (mainObj == null) {
				throw new CellValException(message);
			}
		}
	}

	private Map<String, Integer> setHeaderMap(Sheet sheet) throws Exception {
		Map<String, Integer> map1 = new HashMap<>();
		Row row = null;
		Cell cell = null;
		Iterator<Cell> cellIterator = null;
		row = sheet.getRow(0);
		if (row.getRowNum() == 0) {
			cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				Object object = this.getCellValue(cell);
				map1.put(object.toString().toLowerCase(), cell.getColumnIndex());
			}
		}
		return map1;
	}

	private Object callDependentFunction(Object obj, String name) {
		Method method = null;
		Object ret = null;
		try {
			method = Dao.class.getMethod(name, Object.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			ret = method.invoke(dao, obj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ret;
	}

	private Map<Integer, ConfigObj> setUsefulIndexMap(Map<String, Integer> mainHeaderMap,
			Map<String, Integer> configHeaderMap, List<Object[]> configData) {
		Map<Integer, ConfigObj> useFullIndexMap = new HashMap<>();
		Integer excelNameIndex = configHeaderMap.get("excel name");
		Integer nullIndex = configHeaderMap.get("nullable");
		Integer dependIndex = configHeaderMap.get("dependent");
		Integer dfIndex = configHeaderMap.get("dependent function");
		Integer dbNameIndex = configHeaderMap.get("d.b name");
		Set<Entry<String, Integer>> mainMapEntries = mainHeaderMap.entrySet();
		for (Entry<String, Integer> mainEnrty : mainMapEntries) {
			String mainHeader = mainEnrty.getKey();
			String nullable = null;
			String dependent = null;
			String dependFunc = null;
			String configColName = null;
			String dbName = null;
			for (Object[] configObjs : configData) {
				configColName = this.getValue(configObjs[excelNameIndex], String.class);
				if (configColName != null && mainHeader != null && mainHeader.equalsIgnoreCase(configColName)) {
					ConfigObj co = new ConfigObj();
					nullable = this.getValue(configObjs[nullIndex], String.class);
					dependent = this.getValue(configObjs[dependIndex], String.class);
					dependFunc = this.getValue(configObjs[dfIndex], String.class);
					dbName = this.getValue(configObjs[dbNameIndex], String.class);
					co.setDependent(dependent);
					co.setDependFunc(dependFunc);
					co.setMainColNane(mainHeader);
					co.setNullable(nullable);
					co.setDbColName(dbName);
					useFullIndexMap.put(mainEnrty.getValue(), co);
					System.out.println(co.getDbColName() + "--" + co.getMainColNane() + "--" + mainEnrty.getValue());
					break;
				}
			}
		}
		return useFullIndexMap;
	}

	private Sheet readFile(String path) throws FileNotFoundException, IOException {
		try (FileInputStream file = new FileInputStream(path)) {
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			return sheet;
		}

	}

	private List<Object[]> setData(Sheet sheet, Integer mapSize) throws Exception {
		List<Object[]> listOfList = new ArrayList<>();
		Integer noOfHeaders = mapSize;
		Object[] oArray = null;
		Iterator<Row> rowIterator = sheet.rowIterator();
		Row row = null;
		Cell cell = null;
		Iterator<Cell> cellIterator = null;
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			if (row.getRowNum() > 0) {
				cellIterator = row.cellIterator();
				oArray = new Object[noOfHeaders];
				Integer i = 0;
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					Object object = this.getCellValue(cell);
					if (i < noOfHeaders) {
						oArray[i] = object;
					}
					i++;
				}
				listOfList.add(oArray);
			}

		}

		return listOfList;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(Object obj, Class<T> cls) {
		if (obj != null) {
			return (T) obj;
		}
		return null;
	}

	private Object getCellValue(Cell cell) throws Exception {
		Object value = null;
		if (cell.getCellType().equals(CellType.BOOLEAN)) {
			Boolean val = cell.getBooleanCellValue();
			if (val) {
				value = "TRUE";
			} else {
				value = "FALSE";
			}
		} else if (cell.getCellType().equals(CellType.STRING)) {
			String val = cell.getStringCellValue();
			if (val == null || val.isEmpty() || val.toLowerCase().equals("null")) {
				value = null;
			} else {
				value = val;
			}
		} else if (cell.getCellType().equals(CellType.NUMERIC)) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
			} else {
				value = cell.getNumericCellValue();
			}
		}

		else if (cell.getCellType().equals(CellType.BLANK)) {
			value = null;

		} else if (cell.getCellType().equals(CellType._NONE)) {
			value = null;

		}

		else if (cell.getCellType().equals(CellType.ERROR)) {
			value = null;

		} else if (cell.getCellType().equals(CellType.FORMULA)) {
			value = cell.getNumericCellValue();

		} else {
			System.out.println(cell.getCellType());
			throw new Exception("cell type not supported");
		}

		return value;

	}

	public void creatErrorLog(List<String> errorLog) throws Exception {
		try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\errorLogs.txt")) {
			for (String error : errorLog) {
				byte[] contentInBytes = error.getBytes();
				fileOutputStream.write(contentInBytes);
				fileOutputStream.write(System.getProperty("line.separator").getBytes());
			}
			fileOutputStream.flush();
		}

	}
}
