package com.searchemployeeservice.util;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.indices.IndicesExists;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchemployeeservice.bean.Employee;

public class ElasticSearchUtil {

	private static final String INDEX = "employees";

	private static JestClient client = null;

	private static JestClient getClient() throws Exception {
		if (client == null) {

			JestClientFactory factory = new JestClientFactory();
			factory.setHttpClientConfig(new HttpClientConfig.Builder(
					"https://search-employee-uvtglb6ouhsrjk3spdscwjt7ha.us-west-2.es.amazonaws.com")
					.multiThreaded(true).build());
			JestClient jestClient = factory.getObject();

			boolean indexExists = jestClient.execute(
					new IndicesExists.Builder(".kibana").build()).isSucceeded();

			if (!indexExists) {
				throw new Exception("Index " + INDEX
						+ " not found in elasticsearch.");
			}
			client = jestClient;
		}
		return client;
	}

	/**
	 * Util method to search the employee in elastic search based on the
	 * employee id given as parameter.
	 * 
	 * @param criteria
	 *            Employee id as search criteria
	 * @return {@link Employee} data as JSON Format
	 * @throws Exception
	 */
	public static List<Employee> searchEmployee(String criteria)
			throws Exception {
		if (client == null) {
			client = getClient();
		}

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("empId", criteria));

		Search search = new Search.Builder(searchSourceBuilder.toString())
				.addIndex(".kibana").addType("employee").build();

		SearchResult result = client.execute(search);

		List<Employee> employeeList = new ArrayList<>();
		for (Hit<Employee, Void> hit : result.getHits(Employee.class)) {
			Employee emp = new Employee();

			emp.setEmpId(hit.source.getEmpId());
			emp.setEmpFirstName(hit.source.getEmpFirstName());
			emp.setEmpLastName(hit.source.getEmpLastName());
			emp.setCity(hit.source.getCity());

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String dt = format.format(hit.source.getDob());

			Date date = format.parse(dt);

			emp.setDob(date);

			employeeList.add(emp);
		}

		// String result = convertEmployeesIntoJSonData(employeeList);

		return employeeList;
	}

	/**
	 * Util method to convert JSON employee data into {@link Employee} object
	 * 
	 * @param jSonData
	 *            Employee data as String
	 * @return {@link Employee} object
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Employee convertJSonDataToEmployees(String jSonData)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		Employee emp = mapper.readValue(jSonData, Employee.class);

		return emp;
	}

	/**
	 * Method to save the employee data into Elastic Search database
	 * 
	 * @param employee
	 *            {@link Employee} object data
	 * @return status of the operation as success/failure
	 * @throws Exception
	 */
	public static void saveEmployee(Employee employee) throws Exception {
		if (client == null) {
			client = getClient();
		}

		Map<String, Object> source = createJsonDocument(employee);

		Index index = new Index.Builder(source).index(".kibana").type("employee")
				.build();
		DocumentResult result = client.execute(index);

		System.out.println(result.getResponseCode());

	}

	/**
	 * Method to create {@link Map} of JSON fields and values for the
	 * {@link Employee} object
	 * 
	 * @param emp
	 *            {@link Employee} data
	 * @return {@link Map} of JSON fields and values
	 */
	public static Map<String, Object> createJsonDocument(Employee emp) {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		jsonDocument.put("empId", emp.getEmpId());
		jsonDocument.put("empFirstName", emp.getEmpFirstName());
		jsonDocument.put("empLastName", emp.getEmpLastName());
		jsonDocument.put("city", emp.getCity());
		jsonDocument.put("dob", emp.getDob());
		return jsonDocument;
	}
}
