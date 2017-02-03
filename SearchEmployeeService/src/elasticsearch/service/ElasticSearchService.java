package elasticsearch.service;

import elasticsearch.util.ElasticSearchUtil;
import bean.Employee;

public class ElasticSearchService implements IElasticSearchService,
		IElasticSearchSaveService {

	private static IElasticSearchService service;

	private static IElasticSearchSaveService saveService;

	/**
	 * Private Constructor.
	 */
	private ElasticSearchService() {

	}

	/**
	 * Method to get static {@link IElasticSearchService} instance.
	 * 
	 * @return {@link IElasticSearchService}
	 */
	public static IElasticSearchService getElasticSearchService() {
		service = new ElasticSearchService();
		return service;
	}

	/**
	 * Method to get static {@link IElasticSearchSaveService} instance.
	 * 
	 * @return {@link IElasticSearchSaveService}
	 */
	public static IElasticSearchSaveService getElasticSearchSaveService() {
		saveService = new ElasticSearchService();
		return saveService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * elasticsearch.service.IElasticSearchService#searchEmployee(java.lang.
	 * String)
	 */
	public String searchEmployee(String criteria) throws Exception {

		String empData = ElasticSearchUtil.searchEmployee(criteria);
		return empData;
	}

	/* (non-Javadoc)
	 * @see elasticsearch.service.IElasticSearchSaveService#saveEmployeeDetails(bean.Employee)
	 */
	public String saveEmployeeDetails(Employee employee) throws Exception {

		String status = ElasticSearchUtil.saveEmployee(employee);
		return status;
	}

}
