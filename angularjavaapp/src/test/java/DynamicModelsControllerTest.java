package test.java;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import main.java.com.labyrinth.app.controller.DynamicModelsController;
import test.TestApplicationContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@WebAppConfiguration
public class DynamicModelsControllerTest{

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
	public void getModelStructureTest()
	{	
		try {
			this.mockMvc.perform(get("/api/Structure/Student")
			.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*//dmcTest= new DynamicModelsController();
		Assert.assertNull(dmcTest.getModelStructure("Test"));*/
	}
	
	@Test
	public void getDynamicModelsTest() 
	{	
		try {
			this.mockMvc.perform(get("/api/DynamicModels")
					.accept(contentType))
					.andExpect(status().isOk())
					.andExpect(content().contentType(contentType));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}