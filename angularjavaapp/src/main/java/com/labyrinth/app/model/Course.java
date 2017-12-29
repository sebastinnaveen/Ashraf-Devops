package main.java.com.labyrinth.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Entity
@Table(name="course")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class Course {
	
	@Id
	@GeneratedValue
	@Column(name="course_ID")
	private Integer CourseID; 
	
	@Column(name="course_name")
	private String CourseName;
	
	@Column(name="course_dept")
	private String CourseDept;

	
	public Integer getCourseID() {
		return CourseID;
	}


	public void setCourseID(Integer CourseID) {
		this.CourseID = CourseID;
	}


	public String getCourseName() {
		return CourseName;
	}


	public void setCourseName(String CourseName) {
		this.CourseName = CourseName;
	}


	public String getCourseDept() {
		return CourseDept;
	}


	public void setCourseDept(String CourseDept) {
		this.CourseDept = CourseDept;
	}


	//jackson
	public Course()
	{
		
	}
	
	public Course(Integer CourseID,String CourseName,String CourseDept)
	{
		this.CourseID=CourseID;
		this.CourseName=CourseName;
		this.CourseDept=CourseDept;
	}
	
	
}
