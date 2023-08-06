package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    //sort by last name method
    public List<Employee> findAllByOrderByLastNameAsc();// implemented automagically by spring data jpa
    public List<Employee> findAllByOrderByFirstNameAsc();
	
}
