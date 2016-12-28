package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

public interface ManagerService {
	void createManager(Manager m);

	void updateManager(Manager m);

	void deleteManager(Manager m);

	Manager getManagerById(Long managerId);

	Manager getManagerByEmail(String email);

	Manager getManagerByClubName(String clubName);

	List<Player> getFreePlayersOfClub(Manager m);

	List<Manager> getAllManagers();

	List<Team> getNotCreatedTeamsOfManager(Manager m);
}
