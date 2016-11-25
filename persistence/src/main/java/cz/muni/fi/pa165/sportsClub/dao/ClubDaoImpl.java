package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Club;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
        em.remove(em.merge(club));
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
    
}
