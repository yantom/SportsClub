package cz.muni.fi.pa165.sportsClub.facade;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.dto.PlayerBasicInfoDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
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

	void assignExistingPlayerToTeam(Long playerId, Long teamId, int jerseyNumber);

	void assignNewPlayerToTeam(PlayerDto newPlayer, Long teamId, int jerseyNumber);

	void changeJerseyNumber(Long playerId, Long teamId, int jerseyNumber);

	void removePlayerFromTeam(Long playerInfoId);

	List<PlayerBasicInfoDto> findSuitablePlayersForTeam(Long teamId);

	boolean isJerseyNumberUnique(Long teamId, int jerseyNumber);
}
