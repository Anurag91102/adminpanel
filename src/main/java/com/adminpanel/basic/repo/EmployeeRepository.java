package com.adminpanel.basic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminpanel.basic.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

}
