package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee (Employee employeeToSave);
	
	List<Employee> getAllEmployees();
	
	Optional <Employee> getEmployeeById(long id);
	
	Employee updatedEmployee(Employee employeeToUpdate);
	
	void deleteEmployee(long id);

}
