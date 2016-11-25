package cz.muni.fi.pa165.sportsClub.test;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 * working version, doesn't work exactly as it should
 * 
 * @author Jan Tomasek
 *
 */
public class TestUtils {
	public static void createTestData(EntityManager em, int countOfClubs, int countOfPlayers) {
		em.getTransaction().begin();
		createTestClubs(em, countOfClubs);
		createTestTeams(em);
		createTestPlayers(em, countOfPlayers);
		createPlayerInfos(em);
		em.getTransaction().commit();
	}

	public static void createTestClubs(EntityManager em, int countOfClubs) {
		Club c;
		Manager m;
		for (int i = 0; i < countOfClubs; i++) {
			c = new Club();
			c.setName(i + "clubName");
			m = new Manager();
			m.setEmail(i + "man@mail.com");
			m.setFirstName(i + "fname");
			m.setLastName(i + "lname");
			m.setMobile("+" + i + "");
			m.setPassword(i + "password");
			m.setClub(c);
			c.setManager(m);
			em.persist(c);
		}
	}

	public static void createTestTeams(EntityManager em) {
		List<Club> clubs = em.createQuery("SELECT c FROM Club c", Club.class).getResultList();
		Team t;
		for (Club club : clubs) {
			for (int i = 0; i < Category.values().length; i++) {
				t = new Team();
				t.setCategory(Category.values()[i]);
				t.setManager(club.getManager());
				em.persist(t);
			}
		}
	}

	public static void createTestPlayers(EntityManager em, int countOfPlayers) {
		List<Club> clubs = em.createQuery("SELECT c FROM Club c", Club.class).getResultList();
		int countOfClubs = clubs.size();
		Player p;
		Club c;
		for (int i = 0; i < countOfPlayers; i++) {
			p = new Player();
			p.setEmail(i + "player@mail.com");
			p.setFirstName(i + "fname");
			p.setLastName(i + "lname");
			p.setMobile("+" + i + "");
			p.setHeight(120);
			p.setWeight(120);
			c = clubs.get(i % countOfClubs);
			p.setManager(c.getManager());
			p.setDateOfBirth(categoryToLocalDate(Category.values()[i % Category.values().length]));
			p.setManager(c.getManager());
			em.persist(p);
		}
	}

	public static void createPlayerInfos(EntityManager em) {
		List<Club> clubs = em.createQuery("SELECT c FROM Club c", Club.class).getResultList();
		int countOfClubs = clubs.size();
		Query playerQuery = em.createQuery("SELECT p FROM Player p where p.manager.id = :managerId ", Player.class);
		List<Player> players;
		Query teamQuery = em.createQuery("SELECT p FROM Team p where p.manager.id = :managerId ", Team.class);
		List<Team> teams;

		Player p;
		Club c;
		Long managerId;
		PlayerInfo pi;
		Team t;

		for (int i = 0; i < countOfClubs; i++) {
			managerId = clubs.get(i).getManager().getId();
			playerQuery.setParameter("managerId", managerId);
			teamQuery.setParameter("managerId", managerId);
			players = playerQuery.getResultList();
			teams = teamQuery.getResultList();
			for (int j = 0; j < players.size(); j++) {
				p = players.get(j);
				t = findSuitableTeamBasedOnDOB(p, teams);
				pi = new PlayerInfo();
				pi.setJerseyNumber(j);
				pi.setPlayer(p);
				pi.setPlayerId(p.getId());
				pi.setTeam(t);
				pi.setTeamId(t.getId());
				em.persist(pi);
			}
		}
	}

	private static LocalDate categoryToLocalDate(Category c) {
		int ageLimit = c.getAgeLimit();
		int yearsBeforeNow;
		if (ageLimit == Integer.MAX_VALUE)
			yearsBeforeNow = 50;
		else
			yearsBeforeNow = ageLimit;
		return LocalDate.of(Year.now().getValue() - yearsBeforeNow + 1, 1, 1);
	}

	private static Team findSuitableTeamBasedOnDOB(Player p, List<Team> teams) {
		int suitableLimit = Integer.MAX_VALUE;
		Team suitableTeam = null;

		for (Team t : teams) {
			if (t.getCategory().getAgeLimit() > p.getDateOfBirth().getYear())
				if (t.getCategory().getAgeLimit() <= suitableLimit) {
					suitableLimit = t.getCategory().getAgeLimit();
					suitableTeam = t;
				}
		}

		return suitableTeam;
	}
}
