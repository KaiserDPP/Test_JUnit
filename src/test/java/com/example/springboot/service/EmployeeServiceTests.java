package com.example.springboot.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;

public class EmployeeServiceTests {
	
	private EmployeeRepository employeeRepository;
	
	private EmployeeService employeeService;
	
	//Para testear el service se usa MOCKITO
	@BeforeEach
	public void setup() {
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		employeeService = new EmployeeServiceImpl(employeeRepository);
	}

	@DisplayName("JUnit text for savedEmployee")
	@Test
	public void givenEmail_whenSaveEmployee_thanReturnEmployeeObject() {
		
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();
		
		//Cuando se llama con el email matias@mail.com devuelva un opcional vacio
		BDDMockito.given(employeeRepository.findByEmail(matias.getEmail())).willReturn(Optional.empty());
		
		//Cuando salvemos a matias devolveremos el objeto matias
		BDDMockito.given(employeeRepository.save(matias)).willReturn(matias);
		
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		Employee savedEmployee = employeeService.saveEmployee(matias);
		System.out.println(savedEmployee);
		
		Assertions.assertThat(savedEmployee).isNotNull();
		
	}
}
