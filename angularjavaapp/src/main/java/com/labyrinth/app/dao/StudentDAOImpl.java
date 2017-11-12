package main.java.com.labyrinth.app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.java.com.labyrinth.app.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public StudentDAOImpl() {

	}

	public StudentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Student> list() {
		@SuppressWarnings("unchecked")
		List<Student> listStudent = (List<Student>) sessionFactory.getCurrentSession().createCriteria(Student.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listStudent;
	}

	@Override
	@Transactional
	public Student get(Integer studentID) {
		Session session = sessionFactory.getCurrentSession();
		Criteria studentCriteria = session.createCriteria(Student.class);
		studentCriteria.add(Restrictions.eq("StudentID", studentID));
		Student student = (Student) studentCriteria.uniqueResult();
		if (student != null) {
			return student;
		}
		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdate(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
	}

	@Override
	@Transactional
	public void delete(Integer studentID) {
		Session session = sessionFactory.getCurrentSession();
		Student studentToDelete = new Student();
		studentToDelete.setStudentID(studentID);
		session.delete(studentToDelete);
	}

}
