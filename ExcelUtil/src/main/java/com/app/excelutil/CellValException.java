package com.app.excelutil;

public class CellValException extends Exception {

	private static final long serialVersionUID = 3253523884207267139L;
	private String message;

	public CellValException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
