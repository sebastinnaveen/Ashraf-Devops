package main.java.com.labyrinth.app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.java.com.labyrinth.app.model.Course;

@Repository
public class CourseDAOImpl implements CourseDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public CourseDAOImpl() {

	}

	public CourseDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Course> list() {
		@SuppressWarnings("unchecked")
		List<Course> listCourse = (List<Course>) sessionFactory.getCurrentSession().createCriteria(Course.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listCourse;
	}

	@Override
	@Transactional
	public Course get(Integer courseID) {
		Session session = sessionFactory.getCurrentSession();
		Criteria courseCriteria = session.createCriteria(Course.class);
		courseCriteria.add(Restrictions.eq("CourseID", courseID));
		Course course = (Course) courseCriteria.uniqueResult();
		if (course != null) {
			return course;
		}
		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdate(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(course);
	}

	@Override
	@Transactional
	public void delete(Integer courseID) {
		Session session = sessionFactory.getCurrentSession();
		Course courseToDelete = new Course();
		courseToDelete.setCourseID(courseID);
		session.delete(courseToDelete);
	}

}
