package com.star.sud.employee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.star.sud.account.dao.AccountDao;
import com.star.sud.account.dto.Account;
import com.star.sud.account.model.TAccount;
import com.star.sud.constant.Constants;
import com.star.sud.employee.dao.EmployeeDao;
import com.star.sud.employee.model.TEmployee;
import com.star.sud.employee.service.IEmployeeService;
import com.star.sud.employeer.dto.Employee;
import com.star.sud.status.AppServiceStatus;
import com.star.sud.status.AppServiceStatus.STATUS;

public class EmployeeServiceImpl implements IEmployeeService {

	// Static Attributes
	//////////////////////
	private static final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

	// Attributes
	///////////////
	@Autowired
	@Qualifier("employeeDao")
	private EmployeeDao employeeDao;

	@Autowired
	@Qualifier("accountDao")
	private AccountDao accountDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public AppServiceStatus addEmployee(Employee dto) throws Exception {
		log.debug("addEmployee");
		AppServiceStatus status = new AppServiceStatus();
		try {

			if (null == dto)
				throw new Exception("dto param is null or empty");

			if (null == dto.getAccount())
				throw new Exception("account detaiils is null or empty");

			// Set the Account Details and Persist
			Account accntDto = dto.getAccount();
			TAccount tAccount = new TAccount();
			accntDto.copyBeanProperties(tAccount);
			tAccount.setAccnStatus(Constants.ACTIVE_STATUS);
			tAccount.setAccnId(String.valueOf(System.nanoTime()));
			accountDao.add(tAccount);

			// Capturing Employee Details
			if (StringUtils.isEmpty(dto.getEmpName()))
				throw new Exception("Account Name is Null or Empty");

			TEmployee entity = new TEmployee();
			dto.copyBeanProperties(entity);
			entity.setEmpStatus(Constants.ACTIVE_STATUS);
			entity.settAccount(tAccount);
			entity.setEmpId(String.valueOf(System.nanoTime()));
			employeeDao.add(entity);

			status.setStatus(STATUS.SUCCESS);
		} catch (Exception e) {
			log.error("addEmployee", e);
			status.setStatus(STATUS.FAILED);
			status.setMessage(e.getMessage());
			throw e;
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#getAllEmployees()
	 */
	@Override
	public List<Employee> getAllEmployees() {
		log.debug("getAllEmployees");
		try {
			List<Employee> employeeDetails = new ArrayList<>();

			String query = "from TEmployee o where o.empStatus= :empStatus";

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("empStatus", Constants.ACTIVE_STATUS);

			List<TEmployee> tEmployees = employeeDao.getByQuery(query, parameters);

			tEmployees.stream().forEach(employees -> {
				Employee emDetails = new Employee();
				employees.copyBeanProperties(emDetails);
				employeeDetails.add(emDetails);
			});

			return employeeDetails;
		} catch (Exception e) {
			log.error("getAllEmployees", e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#modifyEmployee(java.lang.
	 * String)
	 */
	@Override
	public Employee modifyEmployee(String radio) {
		log.debug("modifyEmployee");
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			Employee dto = new Employee();

			TEmployee entity = employeeDao.find(radio);
			entity.copyBeanProperties(dto);
			entity.gettAccount().copyBeanProperties(dto.getAccount());

			return dto;

		} catch (Exception e) {
			log.error("modifyEmployee", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#updateEmployee(com.star.sud.
	 * employeer.dto.EmployeeDetails)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public AppServiceStatus updateEmployee(Employee dto) throws Exception {
		log.debug("updateEmployee");
		AppServiceStatus status = new AppServiceStatus();
		try {

			if (null == dto)
				throw new Exception("dto param is null or empty");

			if (null == dto.getEmpId())
				throw new Exception("empId param is null or empty");

			String accountId = dto.getAccount().getAccnId();
			TAccount storedAccn = accountDao.find(accountId);
			dto.getAccount().copyBeanProperties(storedAccn);
			storedAccn.setAccnId(accountId);
			storedAccn.setAccnStatus(Constants.ACTIVE_STATUS);
			accountDao.update(storedAccn);

			// Capturing Employee Details
			if (StringUtils.isEmpty(dto.getEmpName()))
				throw new Exception("Account Name is Null or Empty");
			TEmployee storedEntity = employeeDao.find(dto.getEmpId());

			dto.copyBeanProperties(storedEntity);
			storedEntity.setEmpStatus(Constants.ACTIVE_STATUS);
			storedEntity.settAccount(storedAccn);
			employeeDao.saveOrUpdate(storedEntity);
			status.setStatus(STATUS.SUCCESS);

		} catch (Exception e) {
			log.error("updateEmployee", e);
			status.setStatus(STATUS.FAILED);
			throw e;
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#deleteEmployee(java.lang.
	 * String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public AppServiceStatus deleteEmployee(String radio) throws Exception {
		log.debug("deleteEmployee");
		AppServiceStatus status = new AppServiceStatus();
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			TEmployee entity = employeeDao.find(radio);
			if (null == entity)
				throw new Exception("entity param is null oe empty");

			TAccount tAccount = entity.gettAccount();
			employeeDao.remove(entity);
			accountDao.remove(tAccount);
			status.setStatus(STATUS.SUCCESS);

		} catch (Exception e) {
			log.error("deleteEmployee", e);
			status.setStatus(STATUS.FAILED);
			throw e;
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#viewEmployee(java.lang.String)
	 */
	@Override
	public Employee viewEmployee(String radio) {
		log.debug("viewEmployee");
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			TEmployee entity = employeeDao.find(radio);
			Employee dto = new Employee();
			entity.copyBeanProperties(dto);
			entity.gettAccount().copyBeanProperties(dto.getAccount());
			return dto;

		} catch (Exception e) {
			log.error("viewEmployee", e);
			return null;
		}
	}

}
