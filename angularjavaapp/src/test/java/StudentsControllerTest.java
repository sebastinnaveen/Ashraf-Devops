package test.java;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import main.java.com.labyrinth.app.controller.DynamicModelsController;
import main.java.com.labyrinth.app.model.Student;
import test.TestApplicationContextConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@WebAppConfiguration
public class StudentsControllerTest {
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
	public void createStudentsTest()
	{
		try {
			Student student = new Student();
			student.setEnrollmentDate(new Date());
			student.setFirstMidName("M.");
			student.setLastName("Sable");
			
				System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Student")
					 .requestAttr("student", student)
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8"));	
			//System.out.println(result.andReturn().getResolvedException().getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	@Test
	public void getAllStudentsTest()
	{
		try {
				System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Student")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8"));	
			//System.out.println(result.andReturn().getResolvedException().getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void getStudentTest(){
		try {
				System.out.println("Executing This method");
			ResultActions result = this.mockMvc.perform(get("/api/Student/1")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk());	
			//System.out.println(result.andReturn().getResolvedException().getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
