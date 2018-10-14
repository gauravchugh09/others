package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.bean.User;

@Controller
@RequestMapping("/rest")
public class RestController {
	@ResponseBody
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpSession getStudentList(HttpServletRequest request, HttpServletResponse response) {
		List<User> userList = new ArrayList<>();
		User u1 = new User();
		u1.setCity("rohtak");
		u1.setEmail("gauravchguh02@gmail.com");
		u1.setName("gaurav");
		u1.setPassword("12345");
		// u1.setPhone("9901096579");
		u1.setUserId("gchughh09");

		User u2 = new User();
		u2.setCity("sjfds");
		u2.setEmail("smdfj@gmail.com");
		u2.setName("msd");
		u2.setPassword("0983");
		u2.setPhone("7853478390");
		u2.setUserId("msd09");
		HttpSession session = request.getSession();
		session.setAttribute("user", userList);
		userList.add(u1);
		userList.add(u2);

		return session;
	}

	@ResponseBody
	@RequestMapping(value = "/getUser/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getStudentDetails(@PathVariable("name") String name) {
		User u = new User();
		u.setName(name);
		u.setCity("delhi");
		u.setPhone("990109372");
		return u;
	}

	@ResponseBody
	@RequestMapping(value = "/getUser/{name}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@PathVariable("name") String name, @RequestBody User user) {
		System.out.println("finding user" + name);
		System.out.println("updating user" + user);
		return user;
	}

	@ResponseBody
	@RequestMapping(value = "/getUser1/{name}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser1(@PathVariable("name") String name, @RequestBody User user) {
		System.out.println("finding user" + name);
		System.out.println("updating user" + user);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("key1", "value1");
		httpHeaders.add("key1", "value2");
		ResponseEntity<User> re = new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
		return re;
	}

}
