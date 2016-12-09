package cz.muni.fi.pa165.sportsClub.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 *
 * @author Andrej 410433
 */
@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createTeam(Team team) {
        if(team == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        em.persist(team);
    }

    @Override
    public void deleteTeam(Team team) {
        if(team == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
		if (em.contains(team))
			em.remove(team);
		else
			em.remove(em.getReference(Team.class, team.getId()));
    }

    @Override
    public void updateTeam(Team team) {
        if(team == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        em.merge(team);
    }

    @Override
    public Team getTeamById(Long id) {
        return em.find(Team.class, id);
    }
    
}
