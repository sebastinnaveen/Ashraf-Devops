package main.java.com.labyrinth.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Entity
@Table(name="department")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class Department {
	
	//demo Angular Java
	
	@Id
	@GeneratedValue
	@Column(name="department_ID")
	private Integer DepartmentID;
	
	@Column(name="dept_name")
	private String DeptName;

	
	public Department(Integer DepartmentID, String DeptName) {
		super();
		this.DepartmentID = DepartmentID;
		this.DeptName = DeptName;
	}
	
	//jackson
		public Department()
		{
			
		}

	public Integer getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(Integer DepartmentID) {
		this.DepartmentID = DepartmentID;
	}

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String DeptName) {
		this.DeptName = DeptName;
	}

}
