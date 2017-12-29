package test.java.Dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;  
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import main.java.com.labyrinth.app.dao.CourseDAOImpl;
import main.java.com.labyrinth.app.dao.DepartmentDAO;
import main.java.com.labyrinth.app.model.Course;
import main.java.com.labyrinth.app.model.Department;

import test.TestApplicationContextConfig;  
    
  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseDAOTest {  
  
 @Autowired  
 private main.java.com.labyrinth.app.dao.CourseDAO CourseDAO;  
  
 @Test  
 public void testCreateCourse()  
 {  
  Course course = new Course();  
  course.setCourseDept("IT");
  course.setCourseName("CFD");   
  CourseDAO.saveOrUpdate(course); 
 }  
 
 @Test  
 public void testGetCourseById() {  
  Course course = CourseDAO.get(1);  
  System.out.println(course.toString());
  assertNotNull(course);  
 } 
 
 @Test  
 public void testGetAllCourses() {  
  List<Course> course = CourseDAO.list();  
  System.out.println(course.toString());
  assertNotNull(course);  
 }  
  
}  