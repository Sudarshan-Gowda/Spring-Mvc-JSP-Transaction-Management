package com.star.sud.employee.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.star.sud.constant.Constants;
import com.star.sud.controller.HomePageController;
import com.star.sud.employee.service.IEmployeeService;
import com.star.sud.employeer.dto.Employee;
import com.star.sud.status.AppServiceStatus;
import com.star.sud.status.AppServiceStatus.STATUS;

@Controller
@RequestMapping(value = EmployeeController.ROOT_URL)
public class EmployeeController {

	// Static Attributes
	///////////////////////
	private static final Logger log = Logger.getLogger(HomePageController.class);
	public static final String ROOT_URL = "/employee";
	private static final String MODULE_NAME = "employee";

	// Attributes
	//////////////////
	@Autowired
	@Qualifier("employeeService")
	private IEmployeeService service;

	// Request Mapping Methods
	/////////////////////////////
	@RequestMapping(value = Constants.REQ_MAP_ADD, method = RequestMethod.GET)
	public String addEmployeeDetails(Model model) {
		log.debug("saveEmployeeDetails");
		try {

			model.addAttribute(MODULE_NAME, new Employee());
			model.addAttribute(Constants.RECORD_STATUS, Constants.NEW_RECORD);
			model.addAttribute(Constants.ACTION_URL, ROOT_URL + Constants.REQ_MAP_ADD);

		} catch (Exception e) {
			log.error("saveEmployeeDetails", e);
		}

		return MODULE_NAME + Constants.PATH_SEPERATOR + MODULE_NAME + Constants.ADD_EXT;
	}

	@RequestMapping(value = Constants.REQ_MAP_ADD, method = RequestMethod.POST)
	public String saveEmployeeDetails(Model model, @ModelAttribute Employee dto, RedirectAttributes redirectAttrs) {
		log.debug("saveEmployeeDetails");
		try {

			if (null == dto)
				throw new Exception("dto param is null or empty");

			AppServiceStatus status = service.addEmployee(dto);
			if (status != null && status.getStatus().equals(STATUS.FAILED))
				throw new Exception("Failed to update the employee details!!");
			redirectAttrs.addFlashAttribute("msgsuccess", "Successfully Added!!");

			return Constants.ACTION_REDIRECT + MODULE_NAME + Constants.REQ_MAP_LIST;

		} catch (Exception e) {
			log.error("saveEmployeeDetails", e);
			model.addAttribute("msgdanger", "Failed to Add the details");
			model.addAttribute(Constants.RECORD_STATUS, Constants.NEW_RECORD);

		}

		model.addAttribute(Constants.ACTION_URL, ROOT_URL + Constants.REQ_MAP_ADD);
		return MODULE_NAME + Constants.PATH_SEPERATOR + MODULE_NAME + Constants.ADD_EXT;
	}

	@RequestMapping(value = Constants.REQ_MAP_LIST, method = RequestMethod.GET)
	public String listPage(Model model, @ModelAttribute Employee dto) {
		log.debug("saveEmployeeDetails");
		try {

			List<Employee> employeeDetails = service.getAllEmployees();

			model.addAttribute("dtos", employeeDetails);

		} catch (Exception e) {
			log.error("saveEmployeeDetails", e);
		}

		return MODULE_NAME + Constants.PATH_SEPERATOR + MODULE_NAME + Constants.DATATABLE_EXT;
	}

	@RequestMapping(value = Constants.REQ_MAP_MODIFY, method = RequestMethod.GET)
	public String modifyEmployee(Model model, @RequestParam("radio") String radio) {
		log.debug("modifyEmployee");
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			Employee dto = service.modifyEmployee(radio);
			model.addAttribute(MODULE_NAME, dto);
			model.addAttribute(Constants.RECORD_STATUS, Constants.MODIFY_RECORD);
			model.addAttribute(Constants.ACTION_URL, ROOT_URL + Constants.REQ_MAP_UPDATE);

		} catch (Exception e) {
			log.error("modifyEmployee", e);
		}

		return MODULE_NAME + Constants.PATH_SEPERATOR + MODULE_NAME + Constants.ADD_EXT;

	}

	@RequestMapping(value = Constants.REQ_MAP_UPDATE, method = RequestMethod.POST)
	public String updateEmployee(Model model, @ModelAttribute Employee dto, RedirectAttributes redirectAttrs) {
		log.debug("updateEmployee");
		try {

			if (null == dto)
				throw new Exception("dto param is null or empty");

			AppServiceStatus status = service.updateEmployee(dto);
			if (status != null && status.getStatus().equals(STATUS.SUCCESS)) {
				redirectAttrs.addFlashAttribute("msgsuccess", "Successfully Updated!!");
				return Constants.ACTION_REDIRECT + MODULE_NAME + Constants.REQ_MAP_LIST;
			}

		} catch (Exception e) {
			log.error("updateEmployee", e);

		}
		modifyExt(model, dto);
		return MODULE_NAME + Constants.PATH_SEPERATOR + MODULE_NAME + Constants.ADD_EXT;

	}

	private void modifyExt(Model model, Employee dto) {
		try {
			model.addAttribute(Constants.RECORD_STATUS, Constants.MODIFY_RECORD);
			model.addAttribute("msgdanger", "Failed to Update the details");
			model.addAttribute(MODULE_NAME, dto);
			model.addAttribute(Constants.ACTION_URL, ROOT_URL + Constants.REQ_MAP_UPDATE);

		} catch (Exception e) {
			log.error("modifyExt", e);
		}
	}

	@RequestMapping(value = Constants.REQ_MAP_DELETE, method = RequestMethod.GET)
	public String deleteEmployee(Model model, @RequestParam("radio") String radio, RedirectAttributes redirectAttrs) {
		log.debug("modifyEmployee");
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			AppServiceStatus status = service.deleteEmployee(radio);
			if (status != null && status.getStatus().equals(STATUS.SUCCESS))
				redirectAttrs.addFlashAttribute("msgsuccess", "Successfully deleted!!");
			else
				redirectAttrs.addFlashAttribute("msgsuccess", "Failed to delete the record!!");

		} catch (Exception e) {
			log.error("modifyEmployee", e);
		}

		return Constants.ACTION_REDIRECT + MODULE_NAME + Constants.REQ_MAP_LIST;

	}

	@RequestMapping(value = Constants.REQ_MAP_VIEW, method = RequestMethod.GET)
	public String viewEmployee(Model model, @RequestParam("radio") String radio, RedirectAttributes redirectAttrs) {
		log.debug("modifyEmployee");
		try {

			if (null == radio)
				throw new Exception("radio param is null or empty");

			Employee dto = service.viewEmployee(radio);
			model.addAttribute(MODULE_NAME, dto);
			model.addAttribute(Constants.RECORD_STATUS, Constants.VIEW_RECORD);

		} catch (Exception e) {
			log.error("modifyEmployee", e);
		}

		return MODULE_NAME + Constants.PATH_SEPERATOR + MODULE_NAME + Constants.ADD_EXT;

	}

}
