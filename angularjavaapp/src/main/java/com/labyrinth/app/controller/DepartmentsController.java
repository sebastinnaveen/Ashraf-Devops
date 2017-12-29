package main.java.com.labyrinth.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.labyrinth.app.dao.DepartmentDAO;
import main.java.com.labyrinth.app.model.Department;

@RestController
public class DepartmentsController {

	@Autowired
	private DepartmentDAO departmentDAO;

	List<Department> departmentList = new ArrayList<>();
	ObjectMapper objectMapper = new ObjectMapper();

	//demo Angular Java
	
	// getAll
	@RequestMapping(value = "/api/Department", method = RequestMethod.GET)
	public List<Department> getAllDepartments() {
		departmentList = departmentDAO.list();
		return departmentList;
	}

	// getOne
	@RequestMapping(value = "/api/Department/{departmentID}", method = RequestMethod.GET)
	public Department getDepartment(@PathVariable Integer departmentID) {
		Department department = departmentDAO.get(departmentID);
		return department;
	}

	// update
	@RequestMapping(value = "/api/Department/{departmentID}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void putDepartment(@PathVariable Integer departmentID, @RequestBody Department department)
			throws JsonMappingException, JsonParseException, IOException {
		// Department department=objectMapper.readValue(departmentJSON,
		// Department.class);
		departmentDAO.saveOrUpdate(department);
		// System.out.println(dynamicModelList.toString());
	}

	// delete
	@RequestMapping(value = "/api/Department/{departmentID}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteDepartment(@PathVariable Integer departmentID) {
		departmentDAO.delete(departmentID);
	}

	@RequestMapping(value = "/api/Department", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void createDepartment(@RequestBody Department department)
			throws JsonMappingException, JsonParseException, IOException {
		// Department department=objectMapper.readValue(departmentJSON,
		// Department.class);
		departmentDAO.saveOrUpdate(department);

	}

}
