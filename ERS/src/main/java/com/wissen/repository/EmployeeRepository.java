package com.wissen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wissen.model.Employee;
import com.wissen.model.LoginEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByEmpIdAndPassword(int empId, String password);

	/////////////////////////
	List<Employee> findBy(int center);

	@Query(value = "select * from employee  where center_id= ?1 and level= ?2+1", nativeQuery = true)
	List<Employee> findApproverByCenter(int center_id, int level);
}
