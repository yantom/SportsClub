package cz.muni.fi.pa165.service;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;

public interface ClubService {
	void createClub(Club c);

	void updateClub(Club c);

	void deleteClub(Club c);

	Club getClubById(Long clubId);

	Club getClubByName(String clubName);

	void assignManagerToClub(Manager m, Club c);

	List<Player> getFreePlayers();

	List<Club> getAllClubs();
}
