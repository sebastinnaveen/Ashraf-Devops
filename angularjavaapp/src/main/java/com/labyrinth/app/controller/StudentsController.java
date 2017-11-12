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

import main.java.com.labyrinth.app.dao.StudentDAO;
import main.java.com.labyrinth.app.model.Student;

@RestController
public class StudentsController {

	@Autowired
	private StudentDAO studentDAO;

	List<Student> studentList = new ArrayList<>();
	ObjectMapper objectMapper = new ObjectMapper();

	// getAll
	@RequestMapping(value = "/api/Student", method = RequestMethod.GET)
	public List<Student> getAllStudents() {
		studentList = studentDAO.list();
		return studentList;
	}

	// getOne
	@RequestMapping(value = "/api/Student/{studentID}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable Integer studentID) {
		Student student = studentDAO.get(studentID);
		return student;
	}

	// update
	@RequestMapping(value = "/api/Student/{studentID}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void putStudent(@PathVariable Integer studentID, @RequestBody Student student)
			throws JsonMappingException, JsonParseException, IOException {
		// Student student=objectMapper.readValue(studentJSON, Student.class);
		studentDAO.saveOrUpdate(student);
	}

	// delete
	@RequestMapping(value = "/api/Student/{studentID}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteStudent(@PathVariable Integer studentID) {
		studentDAO.delete(studentID);
	}

	@RequestMapping(value = "/api/Student", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void createStudent(@RequestBody Student student)
			throws JsonMappingException, JsonParseException, IOException {
		// Student student=objectMapper.readValue(studentJSON, Student.class);
		studentDAO.saveOrUpdate(student);

	}

}
