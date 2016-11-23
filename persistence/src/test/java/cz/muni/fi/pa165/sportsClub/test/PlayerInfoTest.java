package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@Rollback(false)
public class PlayerInfoTest {

	@PersistenceContext
	private EntityManager em;

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
		testManager1.addTeam(testTeam1);
		testManager1.addPlayer(testPlayer1);
		testTeam1.addPlayerInfo(testPlayerInfo1);
		testPlayer1.addPlayerInfo(testPlayerInfo1);
		em.persist(testClub1);

		testPlayerInfo1 = new PlayerInfo();
		testPlayerInfo1.setJerseyNumber(10);
		testPlayerInfo1.setPlayer(testPlayer1);
		testPlayerInfo1.setTeam(testTeam1);
		testPlayerInfo1.setPlayerId(testPlayer1.getId());
		testPlayerInfo1.setTeamId(testTeam1.getId());
		em.persist(testPlayerInfo1);
	}

	@After
	public void afterTest() {
		em.remove(testPlayerInfo1);
		if (testPlayerInfo2 != null) {
			em.remove(testPlayerInfo2);
			testPlayerInfo2 = null;
		}
		em.remove(testClub1);
	}

	@Test
	public void testCreatePlayerInfo() {
		Team testTeam2 = new Team();
		testTeam2.setCategory(Category.U19);
		testTeam2.setManager(testManager1);
		testManager1.addTeam(testTeam2);
		em.persist(testTeam2);
		testPlayerInfo2 = new PlayerInfo();
		testPlayerInfo2.setPlayer(testPlayer1);
		testPlayerInfo2.setPlayerId(testPlayer1.getId());
		testPlayerInfo2.setTeam(testTeam2);
		testPlayerInfo2.setTeamId(testTeam2.getId());
		testPlayerInfo2.setJerseyNumber(66);
		playerInfoDao.createPlayerInfo(testPlayerInfo2);
		PlayerInfoId playerInfoId = new PlayerInfoId(testPlayer1.getId(), testTeam2.getId());
		assertEquals(testTeam2, em.find(PlayerInfo.class, playerInfoId).getTeam());
		assertEquals(testPlayer1, em.find(PlayerInfo.class, playerInfoId).getPlayer());
	}

	@Test
	public void updatePlayerInfo() {
		testPlayerInfo1.setJerseyNumber(99);
		playerInfoDao.updatePlayerInfo(testPlayerInfo1);
		PlayerInfoId id = new PlayerInfoId(testPlayerInfo1.getPlayerId(), testPlayerInfo1.getTeamId());
		assertEquals(99, em.find(PlayerInfo.class, id).getJerseyNumber());
	}

	@Test
	public void deletePlayerInfo() {
		PlayerInfoId id = new PlayerInfoId(testPlayerInfo1.getPlayerId(), testPlayerInfo1.getTeamId());
		playerInfoDao.deletePlayerInfo(testPlayerInfo1);
		assertNull(em.find(PlayerInfo.class, id));
	}

	@Test
	public void getPlayerInfoById() {
		PlayerInfoId piID = new PlayerInfoId(testPlayer1.getId(), testTeam1.getId());
		PlayerInfo retrieved = playerInfoDao.getPlayerInfoById(piID);
		assertEquals(testPlayer1, retrieved.getPlayer());
		assertEquals(testTeam1, retrieved.getTeam());
		assertEquals(10, retrieved.getJerseyNumber());
	}

}