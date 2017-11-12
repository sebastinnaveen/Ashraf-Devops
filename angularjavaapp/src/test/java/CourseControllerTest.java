package test.java;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import main.java.com.labyrinth.app.controller.DynamicModelsController;
import main.java.com.labyrinth.app.controller.StudentsController;
import main.java.com.labyrinth.app.model.Course;
import main.java.com.labyrinth.app.model.Department;

import test.TestApplicationContextConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@WebAppConfiguration
public class CourseControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@Autowired
	private DynamicModelsController dmcTest;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Before
	public void initializeDynamicModelsController(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		//this.dmcTest.
	}
	
	
	@Test
	public void getAllCoursesTest()
	{
		try {
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Course")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8"));	
			System.out.println(result.andReturn().getResponse().getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void createCourseTest()
	{
		try {
			Course course = new Course();
			course.setCourseDept("IT");
			course.setCourseID(1);
			course.setCourseName("CFD");
			
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Course/")
					.requestAttr("course", course)
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8"));	
			System.out.println(result.andReturn().getResponse().getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getCourseTest()
	{
		try {
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Course/1")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk());	
			System.out.println(result.andReturn().getResponse().getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void deleteCourseTest()
	{
		try {
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Course/1")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk());	
			System.out.println(result.andReturn().getResponse().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}}
