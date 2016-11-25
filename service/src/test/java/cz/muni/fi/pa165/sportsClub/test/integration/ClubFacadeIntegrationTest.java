package cz.muni.fi.pa165.sportsClub.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.facade.ClubFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.service.ClubService;
import cz.muni.fi.pa165.sportsClub.service.PlayerService;
import cz.muni.fi.pa165.sportsClub.service.TeamService;
import cz.muni.fi.pa165.sportsClub.test.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ClubFacadeIntegrationTest {


    @Inject
	private ClubFacade clubFacade;

	@Inject
	private PlayerService playerService;

	@Inject
	private ClubService clubService;

	@Inject
	private TeamService teamService;

	@PersistenceUnit
	private EntityManagerFactory emc;

	@Before
	public void setup() throws IOException, URISyntaxException {
		EntityManager em = emc.createEntityManager();
		TestUtils.createTestData(em, 2, 40);
		em.close();
	}

	@After
	public void tearDown() throws IOException, URISyntaxException {
		EntityManager em = emc.createEntityManager();
		em.getTransaction().begin();
		for (String line : Files
				.readAllLines(Paths.get(getClass().getClassLoader().getResource("delete.sql").toURI()))) {
			if (line.isEmpty())
				continue;
			em.createNativeQuery(line).executeUpdate();
		}
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void createClub() {
		ClubDto c = new ClubDto();
		c.setName("newClubName");
		clubFacade.createClub(c);
		assertNotNull(c.getId());
	}

	@Test
	public void updateClub() {
		ClubDto c = clubFacade.getClubById(clubFacade.getAllClubs().get(0).getId());
		String cNameBefore = c.getName();
		System.out.println(cNameBefore);
		c.setName("newName");
		clubFacade.updateClub(c);
		String cNameAfter = clubFacade.getClubById(clubFacade.getAllClubs().get(0).getId()).getName();
		System.out.println(cNameAfter);
		assertNotEquals(cNameBefore, cNameAfter);
	}

	//
	@Test
	public void deleteClub() {
		List<ClubDto> clubs = clubFacade.getAllClubs();
		ClubDto c = clubFacade.getClubById(clubs.get(0).getId());
		clubFacade.deleteClub(c);
		assertNotEquals(clubs.size(), clubFacade.getAllClubs().size());
	}

	@Test
	public void getClubById() {
		assertNotNull(clubFacade.getClubById(clubFacade.getAllClubs().get(0).getId()));
	}

	@Test
	public void getClubByName() {
		assertNotNull(clubFacade.getClubByName("1clubName"));
	}

	@Test
	public void assignManagerToClub() {
		ClubDto c = new ClubDto();
		c.setName("newClubName");
		clubFacade.createClub(c);
		assertNull(c.getManager());
		assertNotNull(c.getId());
		ManagerDto m = new ManagerDto();
		m.setEmail("mana@gmail.com");
		m.setFirstName("mana");
		m.setLastName("man");
		m.setPassword("12345678");
		clubFacade.assignManagerToClub(m, c);
		assertNotNull(clubFacade.getClubById(c.getId()).getManager());
	}

	@Test
	public void getAllClubs() {
		assertEquals(2, clubFacade.getAllClubs().size());
	}

	//
	@Test
	public void getFreePlayers() {
		List<ClubDto> clubs = clubFacade.getAllClubs();
		assertEquals(0, clubFacade.getFreePlayers(clubs.get(0)).size());
		Club c = clubService.getAllClubs().get(0);
		List<Player> players = playerService.getAllPlayersOfClub(c);
		Player p = players.get(0);
		List<PlayerInfo> playerInfos = playerService.getPlayerInfos(p);
		PlayerInfo pi = playerInfos.get(0);
		teamService.removePlayerFromTeam(pi.getPlayer(), pi.getTeam());
		assertEquals(1, clubFacade.getFreePlayers(clubs.get(0)).size());
	}
}
