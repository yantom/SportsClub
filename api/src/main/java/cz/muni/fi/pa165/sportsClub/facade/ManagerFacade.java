package cz.muni.fi.pa165.sportsClub.facade;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.dto.AuthenticationDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;

public interface ManagerFacade {

	void createManager(ManagerDto m);

	void updateManager(ManagerDto m);

	void deleteManager(Long id);

	ManagerDto getManagerById(Long managerId);

	ManagerDto getManagerByEmail(String email);

	ManagerDto getManagerByClubName(String clubName);

	List<PlayerDto> getFreePlayers(Long managerId);

	List<ManagerDto> getAllManagers();

	boolean authenticateManager(AuthenticationDto m);

	List<TeamDto> getTeamsOfManager(Long managerId);

	List<TeamDto> getNotAlreadyCreatedTeamsOfManager(Long managerId);
}

