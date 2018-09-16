package test;

import java.util.HashMap;
import java.util.Map;

public class Client {
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<>();
		map.put("x1","1");
		map.put("x2","2");
		map.put("x3", "4");
		map.put("x4","4");
		map.put("x5","4");
		map.put("x6","7");
		
		Service service=new Service();
		service.core(map);
		
	}

}
