package com.wissen.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.ErrorHandler;
import com.wissen.model.Employee;
import com.wissen.model.LoginEmployee;
import com.wissen.model.Token;
import com.wissen.repository.TokenRepository;
import com.wissen.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class LoginController<T> {

	static int i = 0;
	private Map<Integer, String> map = new ConcurrentHashMap<>();
	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	@Autowired
	EmployeeService employeeService;

	@Autowired
	TokenRepository tokenRepository;

	@RequestMapping(value = "/login")
	public List<T> login(@RequestHeader("id") int id, @RequestHeader("password") String password,
			HttpServletResponse response) throws Exception {
		System.out.println(id + " " + password);
		List<T> list = new ArrayList<>();
		Employee employee = employeeService.login(id, password);
		System.out.println(employee.toString());
		if (employee == null) {
			// ystem.out.println("Employee null");
			throw new Exception();
		} else {
			if (tokenRepository.findByEmpId(employee.getEmpId()) != null) {
				System.out.println("IN else if");
				// throw new Exception();
			}
		}
		String sessionId = nextSessionId();
		// map.put(employee.getEmpId(), sessionId);
		Token token = new Token();
		token.setEmpId(employee.getEmpId());
		token.setAuthToken(sessionId);
		token.setId(i++);
		// System.out.println(tokenRepository + " " + token);
		tokenRepository.save(token);
		response.setHeader("token", sessionId);
		list.add((T) sessionId);
		response.setHeader("access-control-expose-header", "token");
		// System.out.println(response.getHeaders(arg0));

		// System.out.println(map);

		list.add((T) employee);

		return list;

	}

	@RequestMapping("/logout")
	@Transactional
	public boolean logout(@RequestHeader String token) throws Exception {

		System.out.println("Logout");
		boolean flag = false;
		// System.out.println(token);
		token = token.trim();

		tokenRepository.deleteByAuthToken(token);
		// System.out.println("Token is " +
		// tokenRepository.findByAuthToken(token).toString());
		// if (tokenRepository.deleteByToken(token) != null)
		// flag = true;
		return flag;

		// for (Entry<Integer, String> set : map.entrySet()) {
		// if (set.getValue().equals(sessionId)) {
		// map.remove(set.getKey());
		// flag = true;
		// }
		// }
		// if (flag)
		// return true;
		// else
		// throw new Exception();
	}

	// @ExceptionHandler(Exception.class)
	public void handleException(HttpServletResponse response) throws Exception {

		ErrorHandler errorHandler = new ErrorHandler();
		errorHandler.setMessage("Not Found");
		errorHandler.setStatusCode("404");
		response.setStatus(404);
		response.setHeader("Message", "Requested Resoiurce not found");
		response.sendError(404, "Requested resource not found");
		// return errorHandler;
	}

}
