package test.java.Dao;

import static org.junit.Assert.assertNotNull;

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

import main.java.com.labyrinth.app.dao.StudentDAO;
import main.java.com.labyrinth.app.model.Student;
import test.TestApplicationContextConfig;  
    
//Jenkins changeset

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoTest {  
  
 @Autowired  
 private StudentDAO studentDAO;  
   
 @Test  
 public void testCreateStudent()  
 {  
  Student student = new Student();  
  student.setFirstMidName("Shrikrishna");
  student.setLastName("Sable");
  student.setEnrollmentDate(new Date());
   studentDAO.saveOrUpdate(student);     
    
 }  
 
 @Test  
 public void testGetStudentById() {  
  Student student = studentDAO.get(1);  
  System.out.println(student.toString());
  assertNotNull(student);  
 }  
   
 
 @Test  
 public void testGetAllStudents() {  
  List<Student> student = studentDAO.list();  
  System.out.println(student.toString());
  assertNotNull(student);  
 }  
  
  
}  