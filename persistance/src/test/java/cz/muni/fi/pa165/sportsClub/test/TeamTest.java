package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 * 
 * @author Jan Tomasek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
public class TeamTest {
	
	@PersistenceUnit
	private EntityManagerFactory emf;

	@PersistenceContext
	private EntityManager emc;

	@Inject
	private TeamDao teamDao;

	private static Team testTeam1;
	
	@Before
	public void beforeTest() {
		testTeam1 = new Team();
		testTeam1.setCategory(Category.MEN);
		emc.persist(testTeam1);
	}

	@After
	public void afterTest() {
		emc.remove(testTeam1);
	}
	
	@Test
	public void testCreateTeam() {
		Team testTeam2 = new Team();
		testTeam2.setCategory(Category.MEN);
		teamDao.createTeam(testTeam2);
		assertTrue(testTeam2.getId() instanceof Long);
		assertEquals(testTeam2, emc.find(Team.class, testTeam2.getId()));
	}

	@Test
	public void updateTeam() {
		testTeam1.setCategory(Category.U13);
		teamDao.updateTeam(testTeam1);
		assertEquals(testTeam1.getCategory(), emc.find(Team.class, testTeam1.getId()).getCategory());
	}

	@Test
	public void deleteTeam() {
		Long teamId = testTeam1.getId();
		teamDao.deleteTeam(testTeam1);
		assertNull(emc.find(Team.class, teamId));
	}

	@Test
	public void getTeamById() {
		Team retrieved = teamDao.getTeamById(testTeam1.getId());
		assertEquals(testTeam1, retrieved);
		assertNull(retrieved.getPlayerInfo());
	}
}
