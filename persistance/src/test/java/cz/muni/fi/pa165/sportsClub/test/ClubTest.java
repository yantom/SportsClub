package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Simon Sudora 461460
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ClubTest {

    private Manager manager;
    private Club club;

    @PersistenceContext
    private EntityManager emc;

    @Inject
    private ClubDao clubDao;

    @Before
    public void makeClub() {
        manager = new Manager();
        manager.setEmail("manager@manager.com");
        manager.setFirstName("Simon");
        manager.setLastName("Sudora");
        manager.setPassword("12345");
        manager.setMobile("0907123456");

        club = new Club();
        club.setName("Sparta Praha");

        manager.setClub(club);
        club.setManager(manager);
    }

    @After
    public void afterTest() {
        clubDao.deleteClub(club);
    }

    @Test
    public void createClubTest(){
        assertNull(club.getId());

        clubDao.createClub(club);
        assertNotNull(club.getId());

        Club savedClub = clubDao.getClubById(club.getId());
        assertEquals(club.getId(),savedClub.getId());
    }

    @Test
    public void updateClubTest(){
        Club originalClub = club;
        clubDao.createClub(originalClub);

        Club updatedClub = originalClub;
        updatedClub.setName("Slavia Praha");
        clubDao.updateClub(updatedClub);
        Club savedClub = clubDao.getClubById(updatedClub.getId());
        assertEquals(originalClub.getId(),savedClub.getId());
        assertEquals(originalClub.getName(),savedClub.getName());
    }

    @Test
    public void deleteClubTest(){
        Club originalClub = club;
        clubDao.createClub(originalClub);
        Long id = club.getId();

        clubDao.deleteClub(club);
        Club deletedClub = clubDao.getClubById(id);
        assertNull(deletedClub);
    }

    @Test
    public void getManagerTest(){
        assertNull(manager.getId());

        clubDao.createClub(club);

        Manager savedManager = club.getManager();
        assertNotNull(savedManager.getId());
    }



}
