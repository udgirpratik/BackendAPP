package com.employee.dao;

import com.employee.connector.CassandraConnector;
import com.employee.exception.EmployeeException;

public class EmployeeDAO {
	
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	private static final String SELECT = "";
	
	public void insertEmployee () throws EmployeeException {
		CassandraConnector cassandraConnector = CassandraConnector.getCassandraConnector();
		cassandraConnector.executeAsynchronusQuery(INSERT);
	}
	
	public void updateEmployee () throws EmployeeException {
		CassandraConnector cassandraConnector = CassandraConnector.getCassandraConnector();
		cassandraConnector.executeAsynchronusQuery(UPDATE);
	}
	
	public void deleteEmployee () throws EmployeeException {
		CassandraConnector cassandraConnector = CassandraConnector.getCassandraConnector();
		cassandraConnector.executeAsynchronusQuery(DELETE);
	}
	
	public void getEmployees () throws EmployeeException {
		CassandraConnector cassandraConnector = CassandraConnector.getCassandraConnector();
		cassandraConnector.executeAsynchronusQuery(SELECT);
	}

}
