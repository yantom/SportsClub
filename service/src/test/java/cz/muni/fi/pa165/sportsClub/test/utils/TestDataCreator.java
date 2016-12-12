package cz.muni.fi.pa165.sportsClub.test.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 * This class contains some method for generating test data and script to fill
 * db with it.
 * 
 * @author Jan Tomasek
 *
 */
public class TestDataCreator {

	private static Long nextId = 10000L;

	public static void createTestDataScript(int countOfClubs, int countOfPlayers) throws IOException {
		StringBuilder script = new StringBuilder();
		script.append(
				"--This script will be overriden each time TestDataUtils createTestDataScript() is called. Serves for testing purposes."
						+ System.lineSeparator());
		List<Club> clubs = createTestClubs(script, countOfClubs);
		List<Team> teams = createTestTeams(script, clubs);
		List<Player> players = createTestPlayers(script, countOfPlayers, clubs);
		createPlayerInfos(script, clubs, teams, players);
		File file = new File("src/test/resources/testInit.sql");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(script.toString());
		}
	}

	public static List<Club> createTestClubs(StringBuilder script, int countOfClubs) {
		Club c;
		Manager m;
		List<Club> clubs = new ArrayList<Club>();
		for (int i = 0; i < countOfClubs; i++) {
			c = new Club();
			c.setName(i + "clubName");
			c.setId(nextId);
			nextId++;
			m = new Manager();
			m.setEmail(i + "man@mail.com");
			m.setFirstName(i + "fname");
			m.setLastName(i + "lname");
			m.setMobile("+" + i + "");
			m.setPassword(i + "password");
			m.setId(c.getId());
			m.setClub(c);
			c.setManager(m);
			clubs.add(c);
			script.append(c.toInsertStatement()).append(m.toInsertStatement());
		}
		return clubs;
	}

	public static List<Team> createTestTeams(StringBuilder script, List<Club> clubs) {
		Team t;
		List<Team> teams = new ArrayList<Team>();
		for (Club club : clubs) {
			for (int i = 0; i < Category.values().length; i++) {
				t = new Team();
				t.setCategory(Category.values()[i]);
				t.setClub(club);
				t.setId(nextId);
				nextId++;
				script.append(t.toInsertStatement());
				teams.add(t);
			}
		}
		return teams;
	}

	public static List<Player> createTestPlayers(StringBuilder script, int countOfPlayers, List<Club> clubs) {
		int countOfClubs = clubs.size();
		Player p;
		Club c;
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < countOfPlayers; i++) {
			p = new Player();
			p.setEmail(i + "player@mail.com");
			p.setFirstName(i + "fname");
			p.setLastName(i + "lname");
			p.setMobile("+" + i + "");
			p.setHeight(120);
			p.setWeight(120);
			p.setId(nextId);
			nextId++;
			c = clubs.get(i % countOfClubs);
			p.setClub(c);
			p.setDateOfBirth(categoryToLocalDate(Category.values()[i % Category.values().length]));
			p.setClub(c);
			script.append(p.toInsertStatement());
			players.add(p);
		}
		return players;
	}

	public static List<PlayerInfo> createPlayerInfos(StringBuilder script, List<Club> clubs, List<Team> allTeams,
			List<Player> allPlayers) {
		List<PlayerInfo> pis = new ArrayList<>();
		int countOfClubs = clubs.size();
		Player p;
		PlayerInfo pi;
		Team t;
		List<Player> playersOfClub;
		List<Team> teamsOfClub;
		for (int i = 0; i < countOfClubs; i++) {
			Long clubId = clubs.get(i).getId();
			playersOfClub = allPlayers.stream().filter(player -> player.getClub().getId() == clubId)
					.collect(Collectors.toList());
			teamsOfClub = allTeams.stream().filter(team -> team.getClub().getId() == clubId)
					.collect(Collectors.toList());
			for (int j = 0; j < playersOfClub.size(); j++) {
				p = playersOfClub.get(j);
				t = findSuitableTeamBasedOnDOB(p, teamsOfClub);
				pi = new PlayerInfo();
				pi.setJerseyNumber(j);
				pi.setPlayer(p);
				pi.setTeam(t);
				pi.setId(nextId);
				nextId++;
				script.append(pi.toInsertStatement());
				pis.add(pi);
			}
		}
		return pis;
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
		LocalDate now = LocalDate.now();
		int ageLimit;

		for (Team t : teams) {
			ageLimit = t.getCategory().getAgeLimit();
			if (ageLimit == Integer.MAX_VALUE || now.minusYears(ageLimit).isBefore(p.getDateOfBirth())) {
				if (ageLimit <= suitableLimit) {
					suitableLimit = ageLimit;
					suitableTeam = t;
				}
			}
		}
		return suitableTeam;
	}
}
