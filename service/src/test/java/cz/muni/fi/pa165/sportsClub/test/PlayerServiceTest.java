package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.service.PlayerService;
import cz.muni.fi.pa165.sportsClub.service.PlayerServiceImpl;
import cz.muni.fi.pa165.sportsClub.service.ServiceApplicationContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author David
 */

@ContextConfiguration(classes = ServiceApplicationContext.class)
public class PlayerServiceTest {
    
    @Inject
    private PlayerService playerService;
    
    
    @Mock
    private PlayerDao playerDao;

    private Player player;
    @Mock
    private Manager manager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playerService = new PlayerServiceImpl();
        LocalDate dt1 = LocalDate.parse("2000-06-15");
        
        player = new Player();
        player.setEmail("onassis@gmail.com");
        player.setFirstName("Aristoteles");
        player.setLastName("Onassis");
        player.setHeight(120);
        player.setWeight(120);
        player.setDateOfBirth(dt1);
        player.setManager(manager);
        
    }

    @Test(expected = RuntimeException.class)
    public void createPlayerTest(){
       doThrow(new RuntimeException()).when(playerDao).createPlayer(player);
       playerService.createPlayer(player);
    }
    
    @Test(expected = RuntimeException.class)
    public void createPlayerWithoutDateTest(){
        player.setDateOfBirth(null);
        doThrow(new RuntimeException()).when(playerDao).createPlayer(player);
        playerDao.createPlayer(player);
    }
    
    @Test
    public void findPlayerByIdTest(){
        when(playerDao.getPlayerById(1L)).thenReturn(player);
        Player returnedPlayer = playerService.getPlayerById(1L);
        assertEquals(player,returnedPlayer);
    }
    
    @Test(expected = RuntimeException.class)
    public void updatePlayerTest() {
        doThrow(new RuntimeException()).when(playerDao).updatePlayer(player);
        playerService.updatePlayer(player);
    }
    
    @Test(expected = DaoLayerException.class)
    public void deletePlayerBeforeSavingTest(){
        playerService.deletePlayer(player);
        verify(playerDao, times(1)).deletePlayer(player);
    }
    
    @Test(expected = RuntimeException.class)
    public void deletePlayerTest() {
        doThrow(new RuntimeException()).when(playerDao).deletePlayer(player);
        playerService.deletePlayer(player);
    }
    
    @Test
    public void getPlayerByEmailTest(){
        when(playerDao.getPlayerByEmail("boris.britva@gmail.com")).thenReturn(player);
        Player returnedPlayer = playerService.getPlayerByEmail("boris.britva@gmail.com");
        assertEquals(player,returnedPlayer);
    }
    
}
