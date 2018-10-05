package com.app.excelutil;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("com/app/excelutil/config.xml");
		Bussiness3 bus = context.getBean(Bussiness3.class);
		try {
			String mainFilePath = "D:\\test.xls";
			String configFilePath = Test.class.getResource("config.xlsx").getPath();
			List<String> errorLog = bus.processData(mainFilePath,configFilePath);
			bus.creatErrorLog(errorLog);

		} catch (Exception e) {
			System.out.println("some unknown error occured in main function.");
		}
	}

}
