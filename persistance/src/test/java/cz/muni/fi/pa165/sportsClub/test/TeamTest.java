package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
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
public class TeamTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private TeamDao teamDao;

	private Team testTeam1;
	private Team testTeam2;
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
		testClub1.setManager(testManager1);
		testManager1.setClub(testClub1);
		testTeam1 = new Team();
		testTeam1.setCategory(Category.MEN);
		testTeam1.setManager(testManager1);
		testManager1.addTeam(testTeam1);
		em.persist(testClub1);
	}

	@After
	public void afterTest() {
		if (testTeam2 != null && testTeam2.getId() != null)
			em.remove(testTeam2);
		em.remove(testClub1);
	}
	
	@Test
	public void createTeam() {
		testTeam2 = new Team();
		testTeam2.setCategory(Category.U13);
		testTeam2.setManager(testManager1);
		teamDao.createTeam(testTeam2);
		assertTrue(testTeam2.getId() instanceof Long);
		assertEquals(testTeam2, em.find(Team.class, testTeam2.getId()));
	}

	@Test
	public void updateTeam() {
		testTeam1.setCategory(Category.U13);
		teamDao.updateTeam(testTeam1);
		assertEquals(testTeam1.getCategory(), em.find(Team.class, testTeam1.getId()).getCategory());
	}

	@Test
	public void deleteTeam() {
		Long teamId = testTeam1.getId();
		teamDao.deleteTeam(testTeam1);
		assertNull(em.find(Team.class, teamId));
	}

	@Test
	public void getTeamById() {
		Team retrieved = teamDao.getTeamById(testTeam1.getId());
		assertEquals(testTeam1, retrieved);
		assertEquals(retrieved.getPlayerInfos().size(), 0);
	}
}
