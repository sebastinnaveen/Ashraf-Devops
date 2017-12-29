package main.java.com.labyrinth.app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.java.com.labyrinth.app.model.Department;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	//demo Angular Java

	@Autowired
	private SessionFactory sessionFactory;

	public DepartmentDAOImpl() {

	}

	public DepartmentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Department> list() {
		@SuppressWarnings("unchecked")
		List<Department> listDepartment = (List<Department>) sessionFactory.getCurrentSession()
				.createCriteria(Department.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listDepartment;
	}

	@Override
	@Transactional
	public Department get(Integer departmentID) {
		Session session = sessionFactory.getCurrentSession();
		Criteria departmentCriteria = session.createCriteria(Department.class);
		departmentCriteria.add(Restrictions.eq("DepartmentID", departmentID));
		Department department = (Department) departmentCriteria.uniqueResult();
		if (department != null) {
			return department;
		}
		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdate(Department department) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(department);

	}

	@Override
	@Transactional
	public void delete(Integer departmentID) {
		Session session = sessionFactory.getCurrentSession();
		Department departmentToDelete = new Department();
		departmentToDelete.setDepartmentID(departmentID);
		session.delete(departmentToDelete);
	}

}
