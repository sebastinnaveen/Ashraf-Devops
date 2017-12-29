package main.java.com.labyrinth.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Entity
@Table(name="student")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class Student {

	@Id
	@GeneratedValue
	@Column(name="student_ID")
	private Integer StudentID; 
	
	@Column(name="first_name")
	private String FirstMidName;
	
	@Column(name="last_name")
	private String LastName;
	
	@Column(name="enrollment_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
	private Date EnrollmentDate;

	
	public Student(Integer StudentID, String FirstMidName, String LastName, Date EnrollmentDate) {
		this.StudentID = StudentID;
		this.FirstMidName = FirstMidName;
		this.LastName = LastName;
		this.EnrollmentDate = EnrollmentDate;
	}
	
	//jackson
	public Student()
	{
		
	}

	public Integer getStudentID() {
		return StudentID;
	}

	public void setStudentID(Integer StudentID) {
		this.StudentID = StudentID;
	}

	public String getFirstMidName() {
		return FirstMidName;
	}

	public void setFirstMidName(String FirstMidName) {
		this.FirstMidName = FirstMidName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	public Date getEnrollmentDate() {
		return EnrollmentDate;
	}

	public void setEnrollmentDate(Date EnrollmentDate) {
		this.EnrollmentDate = EnrollmentDate;
	}
	
}
