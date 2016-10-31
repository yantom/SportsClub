package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * @author Simon Sudora 461460
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ClubTest {

    @Inject
    private ClubDao clubDao;

    @Test
    public void createClubTest(){

    }

    @Test
    public void updateClubTest(){

    }

    @Test
    public void deleteClubTest(){

    }

    @Test
    public void getManagerTest(){

    }

}
