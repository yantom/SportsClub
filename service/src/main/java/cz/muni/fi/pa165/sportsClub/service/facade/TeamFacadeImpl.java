package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.List;
import java.util.Locale.Category;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerOfTeamDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.facade.TeamFacade;

public class TeamFacadeImpl implements TeamFacade {

	public void createTeam(TeamDto t) {
		// TODO Auto-generated method stub

	}

	public void updateTeam(TeamDto t) {
		// TODO Auto-generated method stub

	}

	public void deleteTeam(TeamDto t) {
		// TODO Auto-generated method stub

	}

	public TeamDto getTeamById(Long teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	public TeamDto getTeamOfClubByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TeamDto> getAllTeamsOfClub(ClubDto c) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PlayerOfTeamDto> getPlayersOfTeam(TeamDto t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void assignPlayerToTeam(PlayerDto p, TeamDto t, int jerseyNumber) {
		// TODO Auto-generated method stub

	}

	public void changeJerseyNumber(PlayerDto p, TeamDto t, int jerseyNumber) {
		// TODO Auto-generated method stub

	}

	public void removePlayerFromTeam(PlayerDto p, TeamDto t) {
		// TODO Auto-generated method stub

	}

}
