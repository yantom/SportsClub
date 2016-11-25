package cz.muni.fi.pa165.sportsClub.test;


import cz.muni.fi.pa165.sportsClub.dao.*;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.*;
import cz.muni.fi.pa165.sportsClub.service.ClubService;
import cz.muni.fi.pa165.sportsClub.service.ServiceApplicationContext;
import org.junit.Before;
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
import static org.mockito.Mockito.*;


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
    private Manager mockManager;

    @Mock
    private Club mockClub;

    @Mock
    private Player mockPlayer1;

    @Mock
    private Player mockPlayer2;

    @Mock
    private Player mockPlayer3;

    @Inject
    @InjectMocks
    private ClubService clubService;

    private PlayerInfo playerInfo;
    private Team team;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockManager.setEmail("david@koncak.com");
        mockManager.setPassword("TheBest99");
        mockManager.setFirstName("David");
        mockManager.setLastName("Koncak");

        mockClub.setName("FC Slavia Praha");
        mockClub.setManager(mockManager);
        mockManager.setClub(mockClub);

        team = new Team();
        team.setManager(mockManager);
        team.setCategory(Category.MEN);

        mockPlayer1.setFirstName("Simon");
        mockPlayer1.setLastName("Sudora");
        mockPlayer1.setEmail("simon@sudora.com");
        mockPlayer1.setHeight(180);
        mockPlayer1.setWeight(120);
        mockPlayer1.setDateOfBirth(LocalDate.parse("2000-06-15"));
        mockPlayer1.setManager(mockManager);

        mockPlayer2.setFirstName("Jan");
        mockPlayer2.setLastName("Tomasek");
        mockPlayer2.setEmail("jan@tomasek.com");
        mockPlayer2.setHeight(180);
        mockPlayer2.setWeight(120);
        mockPlayer2.setDateOfBirth(LocalDate.parse("2000-06-15"));
        mockPlayer2.setManager(mockManager);

        mockPlayer3.setFirstName("Andrej");
        mockPlayer3.setLastName("Bonis");
        mockPlayer3.setEmail("andrej@bonis.com");
        mockPlayer3.setHeight(180);
        mockPlayer3.setWeight(120);
        mockPlayer3.setDateOfBirth(LocalDate.parse("2000-06-15"));
        mockPlayer3.setManager(mockManager);

        playerInfo = new PlayerInfo();
        playerInfo.setJerseyNumber(15);
        playerInfo.setPlayer(mockPlayer3);
        playerInfo.setTeam(team);

    }

    @Test(expected = RuntimeException.class)
    public void createClubTest(){
       doThrow(new RuntimeException()).when(clubDao).createClub(mockClub);
       clubService.createClub(mockClub);
    }

    @Test(expected = RuntimeException.class)
    public void deleteClubTest(){
        doThrow(new RuntimeException()).when(clubDao).deleteClub(mockClub);
        clubService.deleteClub(mockClub);
    }

    @Test(expected = RuntimeException.class)
    public void updateClubTest(){
        doThrow(new RuntimeException()).when(clubDao).updateClub(mockClub);
        clubService.updateClub(mockClub);
    }

    @Test
    public void getClubByIdTest(){
        when(clubDao.getClubById(1L)).thenReturn(mockClub);
        Club returnedClub = clubService.getClubById(1L);
        assertEquals(mockClub,returnedClub);
    }

    @Test
    public void getClubByNameTest(){
        when(clubDao.getClubByName("FC Slavia Praha")).thenReturn(mockClub);
        Club returnedClub = clubService.getClubByName("FC Slavia Praha");
        assertEquals(mockClub,returnedClub);
    }

    @Test
    public void getAllClubsTest(){
        List<Club> clubs = new ArrayList<Club>();

        Club club2 = new Club();
        club2.setName("FC Sparta Praha");

        clubs.add(mockClub);
        clubs.add(club2);

        when(clubDao.getAllClubs()).thenReturn(clubs);
        List<Club> returnedClubs = clubService.getAllClubs();

        assertEquals(clubs,returnedClubs);
    }

    @Test
    public void assignManagerTest() {
        Club club = new Club();
        club.setName("FC Sparta Praha");
        club.setId(1L);
        club.setManager(null);

        Manager manager = new Manager();
        manager.setEmail("david@koncak.com");
        manager.setPassword("TheBest99");
        manager.setFirstName("David");
        manager.setLastName("Koncak");

        when(clubDao.getClubById(1l)).thenReturn(club);

        clubService.assignManagerToClub(manager, club);
        assertNotNull(club.getManager());
        assertEquals(club.getManager(), manager);
    }

    @Test
    public void getFreePlayersOfClubTest(){
        List<Player> freePlayers = new ArrayList<Player>();
        freePlayers.add(mockPlayer1);
        freePlayers.add(mockPlayer2);

        List<Player> playersOfClub = new ArrayList<Player>();
        playersOfClub.add(mockPlayer1);
        playersOfClub.add(mockPlayer2);
        playersOfClub.add(mockPlayer3);

        List<PlayerInfo> playerInfos = new ArrayList<PlayerInfo>();
        playerInfos.add(playerInfo);

        when(mockClub.getManager()).thenReturn(mockManager);
        when(mockManager.getPlayers()).thenReturn(playersOfClub);
        when(mockPlayer1.getPlayerInfos()).thenReturn(new ArrayList<PlayerInfo>());
        when(mockPlayer2.getPlayerInfos()).thenReturn(new ArrayList<PlayerInfo>());
        when(mockPlayer3.getPlayerInfos()).thenReturn(playerInfos);


        List<Player> returnedFreePlayers = clubService.getFreePlayersOfClub(mockClub);
        assertEquals(freePlayers,returnedFreePlayers);
    }
}
