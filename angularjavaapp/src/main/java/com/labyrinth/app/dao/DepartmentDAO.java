package main.java.com.labyrinth.app.dao;

import java.util.List;

import main.java.com.labyrinth.app.model.Department;

public interface DepartmentDAO {

	//demo Angular Java

	public List<Department> list();

	public Department get(Integer departmentID);

	public void saveOrUpdate(Department department);

	public void delete(Integer departmentID);
}
