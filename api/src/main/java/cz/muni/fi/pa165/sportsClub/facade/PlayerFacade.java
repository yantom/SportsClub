package cz.muni.fi.pa165.sportsClub.facade;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamOfPlayerDto;

public interface PlayerFacade {
	void createPlayer(PlayerDto p);

	void updatePlayer(PlayerDto p);

	void deletePlayer(Long id);

	PlayerDto getPlayerById(Long playerId);

	PlayerDto getPlayerByEmail(String email);

	List<PlayerDto> getAllPlayersOfClub(Long clubId);

	List<TeamOfPlayerDto> getTeamsOfPlayer(Long playerId);
}
