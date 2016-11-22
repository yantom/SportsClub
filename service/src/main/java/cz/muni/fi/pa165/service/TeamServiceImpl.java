package cz.muni.fi.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

public class TeamServiceImpl implements TeamService {
	@Inject
	TeamDao teamDao;
	@Inject
	PlayerInfoDao playerInfoDao;

	public void createTeam(Team t) {
		// TODO Auto-generated method stub

	}

	public void updateTeam(Team t) {
		// TODO Auto-generated method stub

	}

	public void deleteTeam(Team t) {
		// TODO Auto-generated method stub

	}

	public Team getTeamById(Long teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Team getTeamOfClubByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	public Team getAllTeamsOfClub(Club c) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PlayerInfo> getPlayerInfos(Team t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void assignPlayerToTeam(Player p, Team t) {
		// TODO Auto-generated method stub

	}

	public void changeJerseyNumber(Player p, Team t) {
		// TODO Auto-generated method stub

	}

	public void removePlayerFromTeam(Player p, Team t) {
		// TODO Auto-generated method stub

	}
}
