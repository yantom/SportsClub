package cz.muni.fi.pa165.sportsClub.facade;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.dto.PlayerOfTeamDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.enums.Category;

public interface TeamFacade {
	void createTeam(TeamDto t);

	void updateTeam(TeamDto t);

	void deleteTeam(Long id);

	TeamDto getTeamById(Long teamId);

	TeamDto getTeamOfClubByCategory(Category category, Long clubId);

	List<TeamDto> getAllTeamsOfClub(Long clubId);

	List<PlayerOfTeamDto> getPlayersOfTeam(Long teamId);

	void assignExistingPlayerToTeam(Long pID, Long tID, int jerseyNumber);

	void assignNewPlayerToTeam(Long pID, Long tID, int jerseyNumber);

	void changeJerseyNumber(Long pID, Long tID, int jerseyNumber);

	void removePlayerFromTeam(Long playerInfoId);
        
	boolean isJerseyNumberUnique(Long tID, int jerseyNumber);
}
