package cz.muni.fi.pa165.sportsClub.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.pojo.Manager;

/**
 * @author Jan Tomasek
 */
@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void createManager(Manager m) {
		em.persist(m);
	}

	@Override
	public Manager getManagerById(Long id) {
		return em.find(Manager.class, id);
	}

	@Override
	public Manager getManagerByEmail(String email) {
		TypedQuery<Manager> query = em.createQuery("select m from Manager m where email like :email", Manager.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public void updateManager(Manager m) {
		em.merge(m);
	}

	@Override
	public void deleteManager(Manager m) {
		em.remove(em.merge(m));
	}
}
