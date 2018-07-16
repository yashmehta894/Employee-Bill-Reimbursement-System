package com.wissen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.model.Employee;
import com.wissen.model.LoginEmployee;
import com.wissen.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;

	public Employee login(int empId, String password) {
		return employeeRepo.findByEmpIdAndPassword(empId, password);
	}
}
