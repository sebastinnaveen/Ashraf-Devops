package main.java.com.labyrinth.app.dao;

import java.util.List;

import main.java.com.labyrinth.app.model.Student;

public interface StudentDAO {
	public List<Student> list();

	public Student get(Integer studentID);

	public void saveOrUpdate(Student student);

	public void delete(Integer studentID);
}
