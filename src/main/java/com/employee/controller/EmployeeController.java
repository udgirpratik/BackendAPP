package com.employee.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.employee.model.Employee;

@Path("/")
public class EmployeeController {

	@Path("/employees")
	@Produces(value="application/json")
	@GET
	public String getEmployees() throws JsonGenerationException, JsonMappingException, IOException{
		Employee employee = new Employee("A", "ClassName", "23", new Date());
		Set<Employee> employees = new HashSet<>();
		employees.add(employee);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(employees);
	}
}
