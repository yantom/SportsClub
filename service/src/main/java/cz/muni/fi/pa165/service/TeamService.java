package cz.muni.fi.pa165.service;

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

	Team getTeamOfClubByCategory(Category category);

	Team getAllTeamsOfClub(Club c);

	List<PlayerInfo> getPlayerInfos(Team t);

	void assignPlayerToTeam(Player p, Team t);

	void changeJerseyNumber(Player p, Team t);

	void removePlayerFromTeam(Player p, Team t);
}
