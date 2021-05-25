package com.reactiveWebFlux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;


public class ReactiveRestClientTest {
	
	private static final String baseUrl = "http://localhost:8088/";
	private WebClient webclient = WebClient.create(baseUrl);
	
	ReactiveRestClient reactiveRestClient = new ReactiveRestClient(webclient);
	
	
	@Test
	void getAllEmployees() {
		List<Employee> employeeList = reactiveRestClient.getAllEmployees();
		System.out.println(employeeList);
		for(Employee emp : employeeList) {
			emp.getFirstName();
			emp.getLastName();
			emp.getDesignation();
			emp.getSalary();
		}
		assertTrue(employeeList.size()>0);	
	}

	
	@Test
	void getEmployeeById() {
		int employeeId = 2;
		Employee employee = reactiveRestClient.findEmployeeById(employeeId);
		
		assertEquals("Sahi", employee.getFirstName());
	}
	
	
	
	@Test
	void addNewEmployee() {
		Employee employee = new Employee("Sam","Senior Developer","95000");
		Employee employee1 = reactiveRestClient.addNewEmployee(employee);
		System.out.println("employee1 :" + employee1);
		assertTrue(employee1.getId()!=0);
	}
	//{"firstName":"Sam","designation":"Senior Developer","salary":"95000"}
	
	
	
	@Test 
	void updateEmployee() {
		Employee employee = new Employee("Sree","Senior Developer","95000");
		Employee updatedEmployee = reactiveRestClient.updateEmployee(1, employee);
		
		assertEquals("Sree", updatedEmployee.getFirstName());
		assertEquals("Senior Developer", updatedEmployee.getDesignation());
	}
	//{"firstName":"Sree","designation":"Senior Developer","salary":"95000"}
	
	
	
	@Test
	 void deleteEmployeeById() {
		Employee employee = new Employee("John","Developer","65000");
		Employee employee1 = reactiveRestClient.addNewEmployee(employee);
		System.out.println("employee1 :" + employee1);
		
		String response = reactiveRestClient.deleteEmployeeById(employee1.getId());
		
		System.out.println("***->"+ response);
		System.out.println("***->"+ employee1.getId());
		String expectedMessage = "Deleted employee with id: "+employee1.getId();
		 
		 assertEquals(expectedMessage,response);
		
	}
	
	
}

