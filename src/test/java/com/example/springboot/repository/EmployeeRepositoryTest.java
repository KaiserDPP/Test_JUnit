package com.example.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.springboot.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	// MÉTODO para CREAR un usuario y SALVARLO
	//Introduce 2 CONDICIONES (assertthat), que el empleado no sea nulo y que el id sea mayor que 0
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSaving_thenReturnSavedEmployee() {
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

		Employee savedEmployee = employeeRepository.save(matias);

		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
		System.out.println(savedEmployee);
	}

	// MÉTODO para CREAR 2 usuarios y SALVARLOS
	//Introduce 2 CONDICIONES (assertthat), que el empleado no sea nulo y que tamaño de la lista sea 2
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeList_whenFindAll_thenReturnEmployeeList() {

		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

		Employee david = Employee.builder().firstName("David").lastName("Cortaberria").email("david@mail.com").build();
		
		employeeRepository.save(david);
		employeeRepository.save(matias);

		List<Employee> employees = employeeRepository.findAll();

		assertThat(employees).isNotNull();
		assertThat(employees.size()).isEqualTo(2);
		System.out.println(employees);
	}

	// MÉTODO para CREAR 1 usuario y SALVARLO
	//Luego lo pedimos a la BBDD por Id y lo MOSTRAMOS
	//Introduce 1 CONDICION (assertthat), que el empleado no sea nulo
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

		Employee savedEmployee = employeeRepository.save(matias);

		Employee employee = employeeRepository.findById(savedEmployee.getId()).get();
		//Employee employee = employeeRepository.findById(1L).get();

		assertThat(employee).isNotNull();
		System.out.println(employee);

	}

		// MÉTODO para CREAR 1 usuario y SALVARLO
		//Luego lo pedimos a la BBDD por EMAIL y lo MOSTRAMOS
		//Introduce 2 CONDICIONES (assertthat), que el empleado no sea nulo y que el email coincida con el introducido
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();
		employeeRepository.save(matias);

		//2 maneras
		Employee employee = employeeRepository.findByEmail(matias.getEmail()).get();

		//Employee employee = employeeRepository.findByEmail("matias@mail.com").get();

		
		
		assertThat(employee).isNotNull();
		assertThat(employee.getEmail()).isEqualTo(matias.getEmail());
		System.out.println(employee);

	}

	/* 
	 MÉTODO para CREAR 1 usuario y SALVARLO
	Luego lo MODIFICAMOS y lo volvemos a SALVAR
	Introduce 4 CONDICIONES (assertthat), que el empleado no sea nulo y que las 3 características 
	introducidas coincidan con las del nuevo usuario
	*/
	
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

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

	/* 
	 MÉTODO para CREAR 1 usuario, SALVARLO y luego ELIMINARLO
	Introduce 1 CONDICION (assertthat), que el objeto esté vacio 
	*/
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

		Employee savedEmployee = employeeRepository.save(matias);

		employeeRepository.delete(savedEmployee);

		Optional<Employee> employee = employeeRepository.findById(savedEmployee.getId());

		assertThat(employee).isEmpty();
		System.out.println(employee);

	}
	
	// MÉTODO para CREAR 1 usuario y SALVARLO
	//Luego lo pedimos a la BBDD por NOMBRE y APELLIDO y lo MOSTRAMOS
	//Introduce 3 CONDICIONES (assertthat), que el empleado no sea nulo y que los datos coincidan con los introducidos

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenFirstAndLastName_WhenFindByJPQLindex_ThenReturnEmployeeObject() {
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

		Employee savedEmployee = employeeRepository.save(matias);
		String firstName = savedEmployee.getFirstName();
		String lastName = savedEmployee.getLastName();

		//Employee employee = employeeRepository.findByJPQLindex(firstName, lastName);
		
		Employee employee = employeeRepository.findByJPQLindex("Matias", "Humilde");

		assertThat(employee).isNotNull();
		assertThat(employee.getFirstName()).isEqualTo(firstName);
		assertThat(employee.getLastName()).isEqualTo(lastName);

		System.out.println(employee);
	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenFirstAndLastName_WhenFindByJPQLnamed_ThenReturnEmployeeObject() {
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

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
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

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
		Employee matias = Employee.builder().firstName("Matias").lastName("Humilde").email("matias@mail.com").build();

		Employee savedEmployee = employeeRepository.save(matias);
		String firstName = savedEmployee.getFirstName();
		String lastName = savedEmployee.getLastName();

		Employee employee = employeeRepository.findByNativenames(firstName, lastName);

		assertThat(employee).isNotNull();
		assertThat(employee.getFirstName()).isEqualTo(firstName);
		assertThat(employee.getLastName()).isEqualTo(lastName);

	}
}
