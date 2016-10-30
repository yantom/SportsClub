package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author David Koncak (410155)
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerTest {
    
    @Autowired
    private PlayerDao playerDao;
	
    @PersistenceContext 
    private EntityManager em;
    
    private Player player1;
    private Player player2;
    
    @BeforeMethod
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
        
        player1.setEmail("nula@gmail.com");
        player1.setFirstName("Ales");
        player1.setLastName("Nula");
        player1.setHeight(220);
        player1.setWeight(60);
        player1.setDateOfBirth(dt2);
    }
    
    @Test()
    public void savesName(){
	playerDao.createPlayer(player1);
        playerDao.createPlayer(player2);
	Assert.assertEquals(playerDao.getPlayerById(player1.getId()).getFirstName(), "Aristoteles");
    }
    
    @Test
    public void deletePlayer(){
	playerDao.createPlayer(player1);
	Assert.assertNotNull(playerDao.getPlayerById(player1.getId()));
	playerDao.deletePlayer(player1);
	Assert.assertNull(playerDao.getPlayerById(player1.getId()));
    }
    
    @Test
    public void findByNonExistentId() {
	Assert.assertNull(playerDao.getPlayerById(205648L));
    }
    
    @Test
    public void updatePlayer(){
        playerDao.createPlayer(player1);
        player1.setHeight(230);
        playerDao.updatePlayer(player1);
        Assert.assertEquals(playerDao.getPlayerById(player1.getId()).getHeight(), 230);
    }
    
    
}
