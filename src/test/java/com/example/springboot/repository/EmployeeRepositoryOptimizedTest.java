package com.example.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.springboot.model.Employee;

@DataJpaTest
public class EmployeeRepositoryOptimizedTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee matias;
	
	//Antes de cada test se corre el beforeEach (en este caso se crea el usuario matias)
	@BeforeEach
	public void setup() {
	matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

}
	// Método para crear un usuario y salvarlo
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSaving_thenReturnSavedEmployee() {

		Employee savedEmployee = employeeRepository.save(matias);

		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeList_whenFindAll_thenReturnEmployeeList() {


		Employee david = Employee.builder().firstName("David").lastName("Cortaberria").email("david@mail.com").build();

		employeeRepository.save(david);
		employeeRepository.save(matias);

		List<Employee> employees = employeeRepository.findAll();

		assertThat(employees).isNotNull();
		assertThat(employees.size()).isEqualTo(2);
	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

		Employee savedEmployee = employeeRepository.save(matias);

		Employee employee = employeeRepository.findById(savedEmployee.getId()).get();

		assertThat(employee).isNotNull();
	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
		employeeRepository.save(matias);

		Employee employee = employeeRepository.findByEmail(matias.getEmail()).get();

		assertThat(employee).isNotNull();
		assertThat(employee.getEmail()).isEqualTo(matias.getEmail());

	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

		Employee savedEmployee = employeeRepository.save(matias);

		Employee employeeToUpdate = employeeRepository.findById(savedEmployee.getId()).get();
		employeeToUpdate.setEmail("easily@mail.com");
		employeeToUpdate.setFirstName("Matthew");
		employeeToUpdate.setLastName("Humble");

		Employee updatedEmployee = employeeRepository.save(employeeToUpdate);

		assertThat(updatedEmployee).isNotNull();
		assertThat(updatedEmployee.getEmail()).isEqualTo(employeeToUpdate.getEmail());
		assertThat(updatedEmployee.getFirstName()).isEqualTo(employeeToUpdate.getFirstName());
		assertThat(updatedEmployee.getLastName()).isEqualTo(employeeToUpdate.getLastName());

	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

		Employee savedEmployee = employeeRepository.save(matias);

		employeeRepository.delete(savedEmployee);

		Optional<Employee> employee = employeeRepository.findById(savedEmployee.getId());

		assertThat(employee).isEmpty();

	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenFirstAndLastName_WhenFindByJPQLindex_ThenReturnEmployeeObject() {

		Employee savedEmployee = employeeRepository.save(matias);
		String firstName = savedEmployee.getFirstName();
		String lastName = savedEmployee.getLastName();

		Employee employee = employeeRepository.findByJPQLindex(firstName, lastName);

		assertThat(employee).isNotNull();
		assertThat(employee.getFirstName()).isEqualTo(firstName);
		assertThat(employee.getLastName()).isEqualTo(lastName);

	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenFirstAndLastName_WhenFindByJPQLnamed_ThenReturnEmployeeObject() {

		Employee savedEmployee = employeeRepository.save(matias);
		String firstName = savedEmployee.getFirstName();
		String lastName = savedEmployee.getLastName();

		Employee employee = employeeRepository.findByJPQLnames(firstName, lastName);

		assertThat(employee).isNotNull();
		assertThat(employee.getFirstName()).isEqualTo(firstName);
		assertThat(employee.getLastName()).isEqualTo(lastName);

	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenFirstAndLastName_WhenFindByNativeSQLindex_ThenReturnEmployeeObject() {

		Employee savedEmployee = employeeRepository.save(matias);
		String firstName = savedEmployee.getFirstName();
		String lastName = savedEmployee.getLastName();

		Employee employee = employeeRepository.findByNativeindex(firstName, lastName);

		assertThat(employee).isNotNull();
		assertThat(employee.getFirstName()).isEqualTo(firstName);
		assertThat(employee.getLastName()).isEqualTo(lastName);

	}
	
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenFirstAndLastName_WhenFindByNativeSQLnames_ThenReturnEmployeeObject() {

		Employee savedEmployee = employeeRepository.save(matias);
		String firstName = savedEmployee.getFirstName();
		String lastName = savedEmployee.getLastName();

		Employee employee = employeeRepository.findByNativenames(firstName, lastName);

		assertThat(employee).isNotNull();
		assertThat(employee.getFirstName()).isEqualTo(firstName);
		assertThat(employee.getLastName()).isEqualTo(lastName);

	}
}
