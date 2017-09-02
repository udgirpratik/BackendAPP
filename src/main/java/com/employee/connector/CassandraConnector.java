package com.employee.connector;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.employee.exception.EmployeeException;

public class CassandraConnector {
	
	private static CassandraConnector cassandraConnector = null; 

	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConnector.class);

	private CassandraConnector() throws EmployeeException {
		cassandraProperties = new Properties();
		try {
			cassandraProperties.load(this.getClass().getResourceAsStream("cassandra.properties"));
		} catch (IOException e) {
			LOGGER.error("Error while reading Cassandra Properties");
		}
	}
	
	
	public static final CassandraConnector getCassandraConnector() throws EmployeeException{
		Lock lock = new ReentrantLock();
		lock.lock();
		if(cassandraConnector == null){
			cassandraConnector = new CassandraConnector();
			cassandraConnector.connect();
		}
		lock.unlock();
		return cassandraConnector;
	}
	
	public Session getSession() {
		return session;
	}

	private Properties cassandraProperties;

	private Session session;

	
	private Session connect() {
		PoolingOptions poolingOptions = new PoolingOptions();
		int localCoreConnections = Integer.parseInt(cassandraProperties.getProperty("LOCALCORECONNECTIONS"));
		int localMaxConnections = Integer.parseInt(cassandraProperties.getProperty("LOCALMAXCONNECTIONS"));
		poolingOptions.setConnectionsPerHost(HostDistance.LOCAL, localCoreConnections, localMaxConnections);
		if (session == null) {
			session = Cluster.builder().addContactPoint(cassandraProperties.getProperty("HOST"))
					.withPort(Integer.parseInt(cassandraProperties.getProperty("PORT")))
					.withPoolingOptions(poolingOptions).build().connect();
		}
		return session;
	}

	public void executeSynchronusQuery(String query) {
		session.execute(query);
	}

	public void executeAsynchronusQuery(String query) {
		session.executeAsync(query);
	}

	public void close() {
		session.close();
	}

}
