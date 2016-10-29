package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Team;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        em.persist(team);
    }

    @Override
    public void deleteTeam(Team team) {
        em.remove(team);
    }

    @Override
    public void updateTeam(Team team) {
        em.merge(team);
    }

    @Override
    public Team getTeamById(Long id) {
        return em.find(Team.class, id);
    }
    
}
