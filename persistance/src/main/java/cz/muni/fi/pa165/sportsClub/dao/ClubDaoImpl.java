package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Club;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        em.remove(club);
    }

    @Override
    public void createClub(Club club) {
        em.persist(club);
    }

    @Override
    public Club getClubById(Long id) {
        return em.find(Club.class, id);
    }

    @Override
    public void updateClub(Club club) {
        em.merge(club);
    }
    
}
