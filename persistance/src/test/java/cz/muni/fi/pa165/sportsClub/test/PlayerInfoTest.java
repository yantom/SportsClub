package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfoId;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 * 
 * @author Jan Tomasek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
public class PlayerInfoTest {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@PersistenceContext
	private EntityManager emc;

	@Inject
	private PlayerInfoDao playerInfoDao;

	private static PlayerInfo testPlayerInfo;
	private static Player testPlayer = new Player();
	private static Team testTeam1 = new Team();
	private static Team testTeam2 = new Team();

	@BeforeClass
	public static void setup() {
		testPlayer.setDateOfBirth(LocalDate.of(1994, 5, 30));
		testPlayer.setFirstName("first");
		testPlayer.setLastName("last");
		testPlayer.setEmail("test@gmail.com");
		testPlayer.setDateOfBirth(LocalDate.of(1994, 5, 30));
		testPlayer.setHeight(175);
		testPlayer.setWeight(75);
		testPlayer.setMobile("00421605487986");
		testTeam1.setCategory(Category.MEN);
		testTeam2.setCategory(Category.U19);
	}

	@Before
	public void beforeTest() {
		emc.persist(testTeam1);
		emc.persist(testTeam2);
		emc.persist(testPlayer);
		testPlayerInfo = new PlayerInfo();
		testPlayerInfo.setJerseyNumber(10);
		testPlayerInfo.setPlayer(testPlayer);
		testPlayerInfo.setPlayerId(testPlayer.getId());
		testPlayerInfo.setTeam(testTeam1);
		testPlayerInfo.setTeamId(testTeam1.getId());
		emc.persist(testPlayerInfo);
	}

	@After
	public void afterTest() {
		emc.remove(testTeam1);
		emc.remove(testTeam2);
		emc.remove(testPlayer);
		emc.remove(testPlayerInfo);
		testTeam1.setId(null);
		testTeam2.setId(null);
		testPlayer.setId(null);
	}

	@Test
	public void testCreatePlayerInfo() {
		PlayerInfo testPlayerInfo2 = new PlayerInfo();
		testPlayerInfo2.setPlayer(testPlayer);
		testPlayerInfo2.setPlayerId(testPlayer.getId());
		testPlayerInfo2.setTeam(testTeam2);
		testPlayerInfo2.setTeamId(testTeam2.getId());
		testPlayerInfo2.setJerseyNumber(666);
		playerInfoDao.createPlayerInfo(testPlayerInfo2);
		PlayerInfoId playerInfoId = new PlayerInfoId(testPlayer.getId(), testTeam2.getId());
		assertEquals(testTeam2, emc.find(PlayerInfo.class, playerInfoId).getTeam());
		assertEquals(testPlayer, emc.find(PlayerInfo.class, playerInfoId).getPlayer());
	}

	@Test
	public void updatePlayerInfo() {
		testPlayerInfo.setJerseyNumber(99);
		playerInfoDao.updatePlayerInfo(testPlayerInfo);
		PlayerInfoId id = new PlayerInfoId(testPlayerInfo.getPlayerId(), testPlayerInfo.getTeamId());
		assertEquals(99, emc.find(PlayerInfo.class, id).getJerseyNumber());
	}

	@Test
	public void deletePlayerInfo() {
		PlayerInfoId id = new PlayerInfoId(testPlayerInfo.getPlayerId(), testPlayerInfo.getTeamId());
		playerInfoDao.deletePlayerInfo(testPlayerInfo);
		assertNull(emc.find(PlayerInfo.class, id));
	}

	@Test
	public void getPlayerInfoById() {
		PlayerInfoId piID = new PlayerInfoId(testPlayer.getId(), testTeam1.getId());
		PlayerInfo retrieved = playerInfoDao.getPlayerInfoById(piID);
		assertEquals(testPlayer, retrieved.getPlayer());
		assertEquals(testTeam1, retrieved.getTeam());
		assertEquals(10, retrieved.getJerseyNumber());
	}

}
