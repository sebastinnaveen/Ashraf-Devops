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

import main.java.com.labyrinth.app.dao.DepartmentDAO;
import main.java.com.labyrinth.app.model.Department;

import test.TestApplicationContextConfig;  
    
  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {  

//demo
  
 @Autowired  
 private DepartmentDAO DepartmentDAO;  
 
 @Test  
 public void testCreateDepartment()  
 {  
  Department Department = new Department();  
    Department.setDeptName("IT");
 
  DepartmentDAO.saveOrUpdate(Department); 
 }  
 @Test  
 public void testGetAllDepartments() {  
  List<Department> Department = DepartmentDAO.list();  
  System.out.println(Department.toString());
  assertNotNull(Department);  
 } 
 @Test  
 public void testGetDepartmentById() {  
  Department Department = DepartmentDAO.get(1);  
  System.out.println(Department.toString());
  assertNotNull(Department);  
 } 

  
}  