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
import main.java.com.labyrinth.app.model.Department;

import test.TestApplicationContextConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@WebAppConfiguration
public class DepartmentControllerTest {
	
	//demo
	
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
	public void getAllDepartmentsTest()
	{
		try {
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Department")
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
	public void createDepartmentTest()
	{
		try {
			Department dept= new Department();
			dept.setDeptName("IT");
			dept.setDepartmentID(1);
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Department")
					.requestAttr("department", dept)
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
	public void getDepartmentTest()
	{
		try {
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Department/1")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk());	
			System.out.println(result.andReturn().getResponse().getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void deleteDepartmentTest()
	{
		try {
			System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Department/1")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk());	
			System.out.println(result.andReturn().getResponse().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}}
