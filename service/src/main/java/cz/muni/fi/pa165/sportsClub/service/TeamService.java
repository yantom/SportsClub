package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

public interface TeamService {
	void createTeam(Team t);

	void updateTeam(Team t);

	void deleteTeam(Team t);

	Team getTeamById(Long teamId);

	Team getTeamOfClubByCategory(Category category, Club c);

	List<Team> getAllTeamsOfClub(Club c);

	List<PlayerInfo> getPlayerInfos(Team t);

	void assignExistingPlayerToTeam(Player p, Team t, int jerseyNumber);

	void assignNewPlayerToTeam(Player p, Team t, int jerseyNumber);

	void changeJerseyNumber(Player p, Team t, int jerseyNumber);

	void removePlayerFromTeam(Player p, Team t);

	List<Player> findSuitablePlayersForTeam(Team team);
}
