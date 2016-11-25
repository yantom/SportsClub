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
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


/**
 * @author Simon Sudora 461460
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceApplicationContext.class)
@Transactional
public class ClubServiceTest {

    @Mock
    private ClubDao clubDao;

    @Mock
    private ManagerDao managerDao;

    @Mock
    private PlayerDao playerDao;

    @Mock
    private PlayerInfoDao playerInfoDao;

    @Mock
    private TeamDao teamDao;

    @Mock
    private Manager manager;

    @Mock
    private Club club;

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    @Mock
    private Player player3;

    @Inject
    @InjectMocks
    private ClubService clubService;

    private PlayerInfo playerInfo;
    private Team team;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        manager.setEmail("david@koncak.com");
        manager.setPassword("TheBest99");
        manager.setFirstName("David");
        manager.setLastName("Koncak");

        club.setName("FC Slavia Praha");
        club.setManager(manager);
        manager.setClub(club);

        team = new Team();
        team.setManager(manager);
        team.setCategory(Category.MEN);

        player1.setFirstName("Simon");
        player1.setLastName("Sudora");
        player1.setEmail("simon@sudora.com");
        player1.setHeight(180);
        player1.setWeight(120);
        player1.setDateOfBirth(LocalDate.parse("2000-06-15"));
        player1.setManager(manager);

        player2.setFirstName("Jan");
        player2.setLastName("Tomasek");
        player2.setEmail("jan@tomasek.com");
        player2.setHeight(180);
        player2.setWeight(120);
        player2.setDateOfBirth(LocalDate.parse("2000-06-15"));
        player2.setManager(manager);

        player3.setFirstName("Andrej");
        player3.setLastName("Bonis");
        player3.setEmail("andrej@bonis.com");
        player3.setHeight(180);
        player3.setWeight(120);
        player3.setDateOfBirth(LocalDate.parse("2000-06-15"));
        player3.setManager(manager);

        playerInfo = new PlayerInfo();
        playerInfo.setJerseyNumber(15);
        playerInfo.setPlayer(player3);
        playerInfo.setTeam(team);

    }

    @Test
    public void getFreePlayersTest(){
        List<Player> freePlayers = new ArrayList<Player>();
        freePlayers.add(player1);
        freePlayers.add(player2);

        List<Player> playersOfClub = new ArrayList<Player>();
        playersOfClub.add(player1);
        playersOfClub.add(player2);
        playersOfClub.add(player3);

        List<PlayerInfo> playerInfos = new ArrayList<PlayerInfo>();
        playerInfos.add(playerInfo);

        when(club.getManager()).thenReturn(manager);
        when(manager.getPlayers()).thenReturn(playersOfClub);
        when(player1.getPlayerInfos()).thenReturn(new ArrayList<PlayerInfo>());
        when(player2.getPlayerInfos()).thenReturn(new ArrayList<PlayerInfo>());
        when(player3.getPlayerInfos()).thenReturn(playerInfos);


        List<Player> returnedFreePlayers = clubService.getFreePlayersOfClub(club);

        assertEquals(freePlayers.size(),returnedFreePlayers.size());
        assertTrue(returnedFreePlayers.contains(freePlayers.get(0)));
        assertTrue(returnedFreePlayers.contains(freePlayers.get(1)));
    }
}
