package cz.muni.fi.pa165.sportsClub.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
		if(m == null){
			throw new IllegalArgumentException("Argument can not be null");
		}
		em.persist(m);
	}

	@Override
	public Manager getManagerById(Long id) {

		return em.find(Manager.class, id);
	}

	@Override
	public Manager getManagerByEmail(String email) {
		try{
			return em.createQuery("select m from Manager m where email like :email", Manager.class).
			setParameter("email", email).getSingleResult();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public void updateManager(Manager m) {
		if(m == null){
			throw new IllegalArgumentException("Argument can not be null");
		}
		em.merge(m);
	}

	@Override
	public void deleteManager(Manager m){
		if(m == null){
			throw new IllegalArgumentException("Argument can not be null");
		}
		if (em.contains(m))
			em.remove(m);
		else
			em.remove(em.getReference(Manager.class, m.getId()));
	}
}
