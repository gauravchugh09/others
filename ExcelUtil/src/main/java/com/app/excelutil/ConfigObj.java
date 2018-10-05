package com.app.excelutil;

public class ConfigObj {

	private String mainColNane;
	private String dbColName;
	private String nullable;
	private String dependent;
	private String dependFunc;

	public String getMainColNane() {
		return mainColNane;
	}

	public void setMainColNane(String mainColNane) {
		this.mainColNane = mainColNane;
	}

	public String getDbColName() {
		return dbColName;
	}

	public void setDbColName(String dbColName) {
		this.dbColName = dbColName;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getDependent() {
		return dependent;
	}

	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	public String getDependFunc() {
		return dependFunc;
	}

	public void setDependFunc(String dependFunc) {
		this.dependFunc = dependFunc;
	}

}
