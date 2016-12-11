package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 * 
 * @author Jan Tomasek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class PlayerInfoTest {

	@Inject
	private ClubDao clubDao;

	@Inject
	private TeamDao teamDao;

	@Inject
	private PlayerDao playerDao;

	@Inject
	private PlayerInfoDao playerInfoDao;

	private PlayerInfo testPlayerInfo1;
	private PlayerInfo testPlayerInfo2 = null;
	private Player testPlayer1;
	private Team testTeam1;
	private Manager testManager1;
	private Club testClub1;

	@Before
	public void beforeTest() {
		testManager1 = new Manager();
		testManager1.setEmail("mana@gmail.com");
		testManager1.setFirstName("mana");
		testManager1.setLastName("man");
		testManager1.setPassword("12345678");

		testClub1 = new Club();
		testClub1.setName("sebranka");

		testPlayer1 = new Player();
		testPlayer1.setDateOfBirth(LocalDate.of(1994, 5, 30));
		testPlayer1.setFirstName("first");
		testPlayer1.setLastName("last");
		testPlayer1.setEmail("test@gmail.com");
		testPlayer1.setHeight(175);
		testPlayer1.setWeight(75);
		testPlayer1.setMobile("00421605487986");

		testTeam1 = new Team();
		testTeam1.setCategory(Category.MEN);

		testClub1.setManager(testManager1);
		testManager1.setClub(testClub1);
		testTeam1.setManager(testManager1);
		testPlayer1.setManager(testManager1);


		testPlayerInfo1 = new PlayerInfo();
		testPlayerInfo1.setJerseyNumber(10);
		testPlayerInfo1.setPlayer(testPlayer1);
		testPlayerInfo1.setTeam(testTeam1);

	}

	@After
	public void afterTest() {
		if(testPlayerInfo1 != null)
			playerInfoDao.deletePlayerInfo(testPlayerInfo1);
		playerDao.deletePlayer(testPlayer1);
		teamDao.deleteTeam(testTeam1);
		clubDao.deleteClub(testClub1);
	}

	@Test
	public void testCreatePlayerInfoTest() {
		clubDao.createClub(testClub1);
		playerDao.createPlayer(testPlayer1);
		teamDao.createTeam(testTeam1);
		playerInfoDao.createPlayerInfo(testPlayerInfo1);

		Team testTeam2 = new Team();
		testTeam2.setCategory(Category.U19);
		testTeam2.setManager(testManager1);
		testManager1.addTeam(testTeam2);
		teamDao.createTeam(testTeam2);

		testPlayerInfo2 = new PlayerInfo();
		testPlayerInfo2.setPlayer(testPlayer1);
		testPlayerInfo2.setTeam(testTeam2);
		testPlayerInfo2.setJerseyNumber(66);
		testTeam2.addPlayerInfo(testPlayerInfo2);
		testPlayer1.addPlayerInfo(testPlayerInfo2);
		playerInfoDao.createPlayerInfo(testPlayerInfo2);

		PlayerInfo pi = playerInfoDao.getPlayerInfoById(testPlayerInfo2.getId());

		assertEquals(testTeam2, pi.getTeam());
		assertEquals(testPlayer1, pi.getPlayer());
	}

	@Test
	public void updatePlayerInfoTest() {
		clubDao.createClub(testClub1);
		playerDao.createPlayer(testPlayer1);
		teamDao.createTeam(testTeam1);
		playerInfoDao.createPlayerInfo(testPlayerInfo1);

		testPlayerInfo1.setJerseyNumber(99);
		playerInfoDao.updatePlayerInfo(testPlayerInfo1);
		PlayerInfo pi = playerInfoDao.getPlayerInfoById(testPlayerInfo1.getId());
		assertEquals(99, pi.getJerseyNumber());
	}

	@Test
	public void deletePlayerInfoTest() {
		clubDao.createClub(testClub1);
		teamDao.createTeam(testTeam1);
		playerDao.createPlayer(testPlayer1);
		playerInfoDao.createPlayerInfo(testPlayerInfo1);

		Long id = testPlayerInfo1.getId();
		playerInfoDao.deletePlayerInfo(testPlayerInfo1);

		PlayerInfo deletedPlayerInfo = playerInfoDao.getPlayerInfoById(id);

		assertNull(deletedPlayerInfo);

		testPlayerInfo1 = null;
	}

	@Test
	public void getPlayerInfoByIdTest() {
		clubDao.createClub(testClub1);
		playerDao.createPlayer(testPlayer1);
		teamDao.createTeam(testTeam1);
		playerInfoDao.createPlayerInfo(testPlayerInfo1);

		PlayerInfo retrieved = playerInfoDao.getPlayerInfoById(testPlayerInfo1.getId());
		assertEquals(testPlayer1, retrieved.getPlayer());
		assertEquals(testTeam1, retrieved.getTeam());
		assertEquals(10, retrieved.getJerseyNumber());
	}

	@Test
	public void deletePlayerInfoByTeamAndPlayerTest(){
		clubDao.createClub(testClub1);
		playerDao.createPlayer(testPlayer1);
		teamDao.createTeam(testTeam1);
		playerInfoDao.createPlayerInfo(testPlayerInfo1);

		playerInfoDao.deletePlayerInfoByTeamAndPlayer(testTeam1,testPlayer1);
		PlayerInfo pi = playerInfoDao.getPlayerInfoById(testPlayerInfo1.getId());
		assertNull(pi);
		testPlayerInfo1 = null;
	}

	@Test
	public void changeJerseyNumberTest(){
		clubDao.createClub(testClub1);
		playerDao.createPlayer(testPlayer1);
		teamDao.createTeam(testTeam1);
		playerInfoDao.createPlayerInfo(testPlayerInfo1);

		playerInfoDao.changeJerseyNumber(testTeam1,testPlayer1, 30);
		PlayerInfo pi = playerInfoDao.getPlayerInfoById(testPlayerInfo1.getId());
		assertEquals(30, pi.getJerseyNumber());

	}

}
