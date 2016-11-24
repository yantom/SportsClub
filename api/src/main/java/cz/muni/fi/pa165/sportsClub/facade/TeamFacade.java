package cz.muni.fi.pa165.sportsClub.facade;

import java.util.List;
import java.util.Locale.Category;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerOfTeamDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;

public interface TeamFacade {
	void createTeam(TeamDto t);

	void updateTeam(TeamDto t);

	void deleteTeam(TeamDto t);

	TeamDto getTeamById(Long teamId);

	TeamDto getTeamOfClubByCategory(Category category);

	List<TeamDto> getAllTeamsOfClub(ClubDto c);

	List<PlayerOfTeamDto> getPlayersOfTeam(TeamDto t);

	void assignPlayerToTeam(PlayerDto p, TeamDto t, int jerseyNumber);

	void changeJerseyNumber(PlayerDto p, TeamDto t, int jerseyNumber);

	void removePlayerFromTeam(PlayerDto p, TeamDto t);
}
