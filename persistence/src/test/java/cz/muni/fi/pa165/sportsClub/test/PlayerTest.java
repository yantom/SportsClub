package cz.muni.fi.pa165.sportsClub.test;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Player;

/**
 *
 * @author David Koncak (410155)
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PlayerTest {
    
    @Inject
    private PlayerDao playerDao;
    	
    @PersistenceContext 
    private EntityManager em;
    
    private static Player player1;
    private static Player player2;
    
    @Before
    public void createPlayers(){
        player1 = new Player();
        player2 = new Player();
        LocalDate dt1 = LocalDate.parse("2000-06-15");
        LocalDate dt2 = LocalDate.parse("1950-01-06");
        
        player1.setEmail("onassis@gmail.com");
        player1.setFirstName("Aristoteles");
        player1.setLastName("Onassis");
        player1.setHeight(120);
        player1.setWeight(120);
        player1.setDateOfBirth(dt1);
        
        player2.setEmail("nula@gmail.com");
        player2.setFirstName("Ales");
        player2.setLastName("Nula");
        player2.setHeight(220);
        player2.setWeight(60);
        player2.setDateOfBirth(dt2);
    }
    
    @Test
    public void deletePlayerTest(){
	playerDao.createPlayer(player1);
	Assert.assertNotNull(playerDao.getPlayerById(player1.getId()));
	playerDao.deletePlayer(player1);
	Assert.assertNull(playerDao.getPlayerById(player1.getId()));
    }
    
    @Test
    public void getPlayerByIdTest(){
        playerDao.createPlayer(player1);
        Player found = playerDao.getPlayerById(player1.getId());
        Assert.assertEquals(found, player1);
    }
    
    @Test
    public void findByNonExistentId() {
	Assert.assertNull(playerDao.getPlayerById(205648L));
    }
    
    @Test
    public void updatePlayerTest(){
        playerDao.createPlayer(player1);
        player1.setHeight(230);
        playerDao.updatePlayer(player1);
        Assert.assertEquals(230, playerDao.getPlayerById(player1.getId()).getHeight(), 0.01);
    }
    
    @Test
    public void createPlayerTest(){
        playerDao.createPlayer(player1);
        Assert.assertEquals("Onassis", playerDao.getPlayerById(player1.getId()).getLastName());
    }
    
    @Test
    public void nullIdTest(){
        Assert.assertNull(player1.getId());
        playerDao.createPlayer(player1);
        Assert.assertNotNull(player1.getId());
    }
    
}
