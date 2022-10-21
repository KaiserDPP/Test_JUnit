package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl (EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public Employee saveEmployee(Employee employeeToSave) {
		Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeToSave.getEmail());
		if (existingEmployee.isPresent()) {
			throw new ResourceNotFoundException("Employee already exists given email: " + employeeToSave.getEmail());
		}
		return employeeRepository.save(employeeToSave);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Employee> getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Employee updatedEmployee(Employee employeeToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		
	}

}
