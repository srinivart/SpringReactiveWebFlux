package com.reactiveWebFlux;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;




import static com.reactiveWebFlux.EmployeeConstants.*;


public class ReactiveRestClient {

	Logger logger = LoggerFactory.getLogger(ReactiveRestClient.class);

	private WebClient webclient;

	public ReactiveRestClient(WebClient webclient) {
		this.webclient = webclient;
	}

	public List<Employee> getAllEmployees() {
		return webclient.get()
						.uri(GET_ALL_EMPLOYEES)
						.retrieve()
						.bodyToFlux(Employee.class).collectList().block();

	}

	public Employee findEmployeeById(int employeeId) {
		try {
			return webclient.get()
							.uri(FIND_EMPLOYEE_BY_ID, employeeId)
							.retrieve()
							.bodyToMono(Employee.class)
							.block(); 																												// synchronous
		} catch (WebClientResponseException ex) {
			logger.error("Error Response Code is {} and the response body is {} ", ex.getRawStatusCode(),
					ex.getResponseBodyAsString());
			logger.error("WebClientResponseException in retrieveEmployeeById", ex);
			throw ex;
		} catch (Exception ex) {
			logger.error("Exception in retrieveEmployeeById", ex);
			throw ex;
		}
	}

	public Employee addNewEmployee(Employee employee) {

		try {
			return webclient.post()
					.uri(ADD_NEW_EMPLOYEE)
					.syncBody(employee)
					.retrieve()
					.bodyToMono(Employee.class)
					.block();
			
		} catch (WebClientResponseException ex) {
			logger.error("Error Response Code is {} and the response body is {} ", ex.getRawStatusCode(),
					ex.getResponseBodyAsString());
			logger.error("WebClientResponseException in addNewEmployee", ex);
			throw ex;
		} catch (Exception ex) {
			logger.error("Exception in addNewEmployee", ex);
			throw ex;
		}
	}

	public Employee updateEmployee(int employeeId, Employee employee) {

		try {
			return webclient.put()
							.uri(UPDATE_EMPLOYEE_BY_ID, employeeId)
							.syncBody(employee)
							.retrieve()
							.bodyToMono(Employee.class).block();
			
		} catch (WebClientResponseException ex) {
			logger.error("Error Response Code is {} and the response body is {} ", ex.getRawStatusCode(),
					ex.getResponseBodyAsString());
			logger.error("WebClientResponseException in updateEmployee", ex);
			throw ex;
		} catch (Exception ex) {
			logger.error("Exception in updateEmployee", ex);
			throw ex;
		}
	}

	public String deleteEmployeeById(int employeeId) {
		return webclient.delete()
						.uri(DELETE_EMPLOYEE_BY_ID, employeeId)
						.retrieve().bodyToMono(String.class)
						.block();

	}

}
