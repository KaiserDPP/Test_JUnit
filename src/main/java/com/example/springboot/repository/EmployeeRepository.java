package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	@Query("SELECT e from Employee e where e.firstName = ?1 and e.lastName =?2")
	Employee findByJPQLindex(String firstName, String lastName);

	@Query("SELECT e from Employee e where e.firstName =:firstName and e.lastName =:lastName")
	Employee findByJPQLnames(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query(value = "SELECT * from employees e where e.first_name = ?1 and e.last_name =?2", nativeQuery = true)
	Employee findByNativeindex(String firstName, String lastName);

	@Query(value = "SELECT * from employees e where e.first_name =:firstName and e.last_name =:lastName", nativeQuery = true)
	Employee findByNativenames(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
