package com.springremotingclient.service;

public interface IElasticSearchService {
	/**
	 * Method to search employee based on the search criteria given from UI.
	 * This method will return the details in JSON format.
	 * 
	 * @param criteria
	 *            String criteria entered at UI
	 * @return JSON format String employee search results
	 * @throws Exception 
	 */
	public String searchEmployee(String criteria) throws Exception;

}
