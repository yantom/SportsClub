package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.sportsClub.ServiceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.facade.ClubFacade;
import cz.muni.fi.pa165.sportsClub.testUtils.ScriptRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceApplicationContext.class)
public class ClubFacadeIntegrationTest {

	@Inject
	private ClubFacade clubFacade;

	@Inject
	private DataSource ds;

	@Before
	public void setup() throws FileNotFoundException, IOException, SQLException {
		try (Connection conn = ds.getConnection()) {
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.runScript(new BufferedReader(new FileReader("src/test/resources/testInit.sql")));
		}
	}

	@After
	public void tearDown() throws IOException, URISyntaxException, SQLException {
		try (Connection conn = ds.getConnection()) {
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.runScript(new BufferedReader(new FileReader("src/test/resources/delete.sql")));
		}
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
		c.setName("newName");
		clubFacade.updateClub(c);
		String cNameAfter = clubFacade.getClubById(clubFacade.getAllClubs().get(0).getId()).getName();
		assertNotEquals(cNameBefore, cNameAfter);
	}

	@Test
	public void getClubById() {
		ClubDto c = clubFacade.getClubById(clubFacade.getAllClubs().get(0).getId());
		assertNotNull(c);
		assertNotNull(c.getManager());
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
		clubFacade.assignManagerToClub(m, c.getId());
		assertNotNull(clubFacade.getClubById(c.getId()).getManager());
	}

	@Test
	public void getAllClubs() {
		assertEquals(2, clubFacade.getAllClubs().size());
	}
}
