package cz.muni.fi.pa165.sportsClub.test;


import cz.muni.fi.pa165.sportsClub.dao.*;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.*;
import cz.muni.fi.pa165.sportsClub.service.ClubService;
import cz.muni.fi.pa165.sportsClub.service.ServiceApplicationContext;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Simon Sudora 461460
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceApplicationContext.class)
public class ClubServiceTest {

    @Mock
    ClubDao clubDao;

    @Mock
    ManagerDao managerDao;

    @Mock
    PlayerDao playerDao;

    @Mock
    PlayerInfoDao playerInfoDao;

    @Mock
    TeamDao teamDao;

    @Inject
    @InjectMocks
    private ClubService clubService;

    @Before
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFreePlayersTest(){

        Manager manager = new Manager();
        manager.setEmail("david@koncak.com");
        manager.setPassword("TheBest99");
        manager.setFirstName("David");
        manager.setLastName("Koncak");

        Club club = new Club();
        club.setName("FC Slavia Praha");
        club.setManager(manager);

        manager.setClub(club);
        managerDao.createManager(manager);
        clubDao.createClub(club);

        Team team = new Team();
        team.setManager(manager);
        team.setCategory(Category.MEN);
        teamDao.createTeam(team);


        //player of club without team
        Player player1 = new Player();
        player1.setFirstName("Simon");
        player1.setLastName("Sudora");
        player1.setEmail("simon@sudora.com");
        player1.setHeight(180);
        player1.setWeight(120);
        player1.setDateOfBirth(LocalDate.parse("2000-06-15"));
        player1.setManager(manager);
        playerDao.createPlayer(player1);

        //player of club without team
        Player player2 = new Player();
        player2.setFirstName("Jan");
        player2.setLastName("Tomasek");
        player2.setEmail("jan@tomasek.com");
        player2.setHeight(180);
        player2.setWeight(120);
        player2.setDateOfBirth(LocalDate.parse("2000-06-15"));
        player2.setManager(manager);
        playerDao.createPlayer(player2);


        //player of club with team
        Player player3 = new Player();
        player3.setFirstName("Andrej");
        player3.setLastName("Bonis");
        player3.setEmail("andrej@bonis.com");
        player3.setHeight(180);
        player3.setWeight(120);
        player3.setDateOfBirth(LocalDate.parse("2000-06-15"));
        player3.setManager(manager);
        playerDao.createPlayer(player3);


        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setJerseyNumber(15);
        playerInfo.setPlayer(player3);
        playerInfo.setTeam(team);
        playerInfoDao.createPlayerInfo(playerInfo);

        List<Player> freePlayers = new ArrayList<Player>();
        freePlayers.add(player1);
        freePlayers.add(player2);

        List<Player> returnedFreePlayers = clubService.getFreePlayersOfClub(club);

        assertEquals(freePlayers.size(),returnedFreePlayers.size());
        assertTrue(returnedFreePlayers.contains(freePlayers.get(0)));
        assertTrue(returnedFreePlayers.contains(freePlayers.get(1)));

    }
}
