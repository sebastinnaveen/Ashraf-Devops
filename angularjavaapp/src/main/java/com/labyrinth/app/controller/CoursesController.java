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

import main.java.com.labyrinth.app.dao.CourseDAO;
import main.java.com.labyrinth.app.model.Course;

@RestController
public class CoursesController {

	@Autowired
	private CourseDAO courseDAO;

	List<Course> courseList = new ArrayList<>();
	ObjectMapper objectMapper = new ObjectMapper();

	// getAll
	@RequestMapping(value = "/api/Course", method = RequestMethod.GET)
	public List<Course> getAllCourses() {
		courseList = courseDAO.list();
		return courseList;
	}

	// getOne
	@RequestMapping(value = "/api/Course/{courseID}", method = RequestMethod.GET)
	public Course getCourse(@PathVariable Integer courseID) {
		Course course = courseDAO.get(courseID);
		return course;
	}

	// update
	@RequestMapping(value = "/api/Course/{courseID}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void putCourse(@PathVariable Integer courseID, @RequestBody Course course)
			throws JsonMappingException, JsonParseException, IOException {
		// Student student=objectMapper.readValue(studentJSON, Student.class);
		courseDAO.saveOrUpdate(course);
	}

	// delete
	@RequestMapping(value = "/api/Course/{courseID}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteCourse(@PathVariable Integer courseID) {
		courseDAO.delete(courseID);
	}

	@RequestMapping(value = "/api/Course", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void createCourse(@RequestBody Course course) throws JsonMappingException, JsonParseException, IOException {
		// Student student=objectMapper.readValue(studentJSON, Student.class);
		courseDAO.saveOrUpdate(course);

	}
}
