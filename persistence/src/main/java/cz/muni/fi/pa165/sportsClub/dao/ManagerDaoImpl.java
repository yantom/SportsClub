package cz.muni.fi.pa165.sportsClub.dao;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

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

	@Override
	public Manager getManagerByClubName(String name) {
		try {
			return em.createQuery("select m from Manager m where clubName = :name", Manager.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public List<Manager> getAllManagers() {
		return Collections.unmodifiableList(em.createQuery("SELECT m FROM Manager m", Manager.class).getResultList());
	}

	@Override
	public Manager getManagerWithAllData(Long managerId) {
		Manager m = getManagerById(managerId);
		List<Player> players = m.getPlayers();
		List<Team> teams = m.getTeams();
		for (Team team : teams) {
			team.getPlayerInfos().size();
		}
		for (Player player : players) {
			player.getPlayerInfos().size();
		}
		return m;
	}

	@Override
	public List<Player> getFreePlayers(Manager manager) {
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p " + "LEFT JOIN p.playerInfos pi "
				+ "WHERE p.manager = :manager " + "AND pi.player = null", Player.class);
		query.setParameter("manager", manager);
		return query.getResultList();
	}

	@Override
	public List<Player> getPlayersWithHigherDobThan(Manager manager, LocalDate ageLimitDate) {
		TypedQuery<Player> query = em.createQuery(
				"SELECT p FROM Player p " + "WHERE p.manager = :manager " + "AND p.dateOfBirth >= :ageLimitDate",
				Player.class);
		query.setParameter("manager", manager);
		query.setParameter("ageLimitDate", ageLimitDate);
		return query.getResultList();
	}
}
