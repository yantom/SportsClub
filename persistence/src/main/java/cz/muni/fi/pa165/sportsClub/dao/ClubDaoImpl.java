package cz.muni.fi.pa165.sportsClub.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 *
 * @author David Koncak (410155)
 */

@Repository
@Transactional
public class ClubDaoImpl implements ClubDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void deleteClub(Club club) {
        if(club == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
		if (em.contains(club))
			em.remove(club);
		else
			em.remove(em.getReference(Club.class, club.getId()));
    }

    @Override
    public void createClub(Club club){
        if(club == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        em.persist(club);
    }

    @Override
    public Club getClubById(Long id) {
        return em.find(Club.class, id);
    }

    @Override
    public void updateClub(Club club) {
        if(club == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        em.merge(club);
    }

    @Override
    public Club getClubByName(String name) {
        try {
            return em.createQuery("select c from Club c where name = :name",
                                Club.class).setParameter("name", name).getSingleResult();
	} catch (NoResultException nrf) {
            return null;
	}
    }

    @Override
    public List<Club> getAllClubs() {
        return Collections.unmodifiableList(
                em.createQuery("SELECT c FROM Club c", Club.class).getResultList());
    }

	@Override
	public Club getWholeClubById(Long clubId) {
		Club c = getClubById(clubId);
		List<Player> players = c.getPlayers();
		List<Team> teams = c.getTeams();
		for (Team team : teams) {
			team.getPlayerInfos().size();
		}
		for (Player player : players) {
			player.getPlayerInfos().size();
		}
		return c;
	}

    @Override
    public List<Player> getFreePlayers(Club club) {
        TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p " +
                                                "LEFT JOIN p.playerInfos pi " +
                                                "WHERE p.club = :club " +
                                                "AND pi.player = null", Player.class);
        query.setParameter("club", club);
        return query.getResultList();
    }
}
