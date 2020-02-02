package com.star.sud.employee.service;

import java.util.List;

import com.star.sud.employeer.dto.Employee;
import com.star.sud.status.AppServiceStatus;

public interface IEmployeeService {

	AppServiceStatus addEmployee(Employee dto) throws Exception;

	/**
	 * @return
	 */
	List<Employee> getAllEmployees();

	/**
	 * @param radio
	 * @return
	 * @throws Exception
	 */
	Employee modifyEmployee(String radio);

	/**
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	AppServiceStatus updateEmployee(Employee dto) throws Exception;

	/**
	 * @param radio
	 * @return
	 * @throws Exception 
	 */
	AppServiceStatus deleteEmployee(String radio) throws Exception;

	/**
	 * @param radio
	 * @return
	 */
	Employee viewEmployee(String radio);

}
