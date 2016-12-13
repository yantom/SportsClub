package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.ClubDao;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Simon Sudora 461460
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ClubTest {

    private Manager manager;
    private Club club;

    @Inject
    private ClubDao clubDao;

    @Inject
    private PlayerDao playerDao;

    @Inject
    private PlayerInfoDao playerInfoDao;

    @Inject
    private TeamDao teamDao;

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
		if (club != null)
			clubDao.deleteClub(club);
    }

    @Test()
    public void createClubTest(){
        assertNull(club.getId());
        clubDao.createClub(club);
        assertNotNull(club.getId());

        Club savedClub = clubDao.getClubById(club.getId());
        assertEquals(club.getId(),savedClub.getId());
    }

    @Test(expected = DataAccessException.class)
    public void createExistingClubTest(){
        assertNull(club.getId());

        clubDao.createClub(club);
        assertNotNull(club.getId());

        Club savedClub = clubDao.getClubById(club.getId());
        assertEquals(club.getId(),savedClub.getId());
        clubDao.createClub(club);
    }

    @Test(expected = DataAccessException.class)
    public void createClubWithExistingNameTest(){
        assertNull(club.getId());

        clubDao.createClub(club);
        assertNotNull(club.getId());

        Club savedClub = clubDao.getClubById(club.getId());
        assertEquals(club.getId(),savedClub.getId());
        Club club2 = new Club();
        club2.setManager(manager);
        club2.setName(club.getName());
        clubDao.createClub(club2);
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
        clubDao.createClub(club);
        Long id = club.getId();

        clubDao.deleteClub(club);
        Club deletedClub = clubDao.getClubById(id);
        assertNull(deletedClub);
		club = null;
    }

    @Test
    public void getManagerTest(){
        assertNull(manager.getId());

        clubDao.createClub(club);

        Manager savedManager = club.getManager();
        assertNotNull(savedManager.getId());
    }

    @Test
    public void getFreePlayers(){
        clubDao.createClub(club);
        assertNotNull(club.getId());

        Player player1 = new Player();
        player1.setEmail("onassis@gmail.com");
        player1.setFirstName("Aristoteles");
        player1.setLastName("Onassis");
        player1.setHeight(120);
        player1.setWeight(120);
        player1.setDateOfBirth(LocalDate.parse("2000-06-15"));
        player1.setClub(club);

        Player player2 = new Player();
        player2.setEmail("pepapppp@noooooovak.com");
        player2.setFirstName("Pepa");
        player2.setLastName("Novak");
        player2.setHeight(150);
        player2.setWeight(67);
        player2.setMobile("+42190000000");
        player2.setDateOfBirth(LocalDate.parse("1991-06-15"));
        player2.setClub(club);

        playerDao.createPlayer(player1);
        playerDao.createPlayer(player2);
        assertNotNull(player1.getId());
        assertNotNull(player2.getId());


        Team team = new Team();
        team.setCategory(Category.MEN);
        team.setClub(club);
        teamDao.createTeam(team);

        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setJerseyNumber(10);
        playerInfo.setPlayer(player1);
        playerInfo.setTeam(team);
        playerInfoDao.createPlayerInfo(playerInfo);

        List<Player> freePlayers = clubDao.getFreePlayers(club);

        assertEquals(1, freePlayers.size());
        assertEquals(player2, freePlayers.get(0));

    }
}
