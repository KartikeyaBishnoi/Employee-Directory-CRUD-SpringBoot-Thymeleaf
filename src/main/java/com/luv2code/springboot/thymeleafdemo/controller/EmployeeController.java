package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel)
	{
		List<Employee> theEmployees = employeeService.findAllByOrderByFirstNameAsc();


		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{

		//create model attribute to bind form data

		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel)
	{
		// get Employee frpm service
		Employee theEmployee = employeeService.findById(theId);

		//set the employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);

		// redirect to employee form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployees(@ModelAttribute("employee") Employee theEmployee)
	{
		//save the employee
		employeeService.save(theEmployee);
		// use redirect to prevent duplicate submissions
		return"redirect:/employees/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId)
	{
		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}




}









