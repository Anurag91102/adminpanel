package com.adminpanel.basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adminpanel.basic.model.Employee;
import com.adminpanel.basic.repo.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	private EmployeeRepository employeeRepo;
	
	//to save an employee
	public Employee save(Employee employee)
	{
		return 	employeeRepo.save(employee); 
	}
	
	//get all details of employee
	public List<Employee> getAll()
	{
		List<Employee>  employee = (List<Employee>) employeeRepo.findAll();
		return employee;
	}
	
	
	// delete an employee based on id
	public void deleteDetial(int id)
	{
		employeeRepo.deleteById(id);
	}
	
	//get details by id
	public Optional<Employee> getDetails(int id)
	{
		return employeeRepo.findById(id);

	 
	}
	
}
