package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ManagerAuthenticationDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.facade.ManagerFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.service.BeanMappingService;
import cz.muni.fi.pa165.sportsClub.service.ManagerService;

@Transactional
@Service
public class ManagerFacadeImpl implements ManagerFacade {

    @Inject
	ManagerService managerService;

	@Inject
	private BeanMappingService beanMappingService;

	@Override
	public void createManager(ManagerDto m) {
		Manager managerEntity = beanMappingService.mapTo(m, Manager.class);
		managerService.createManager(managerEntity);
		m.setId(managerEntity.getId());
	}

	@Override
	public void updateManager(ManagerDto m) {
		managerService.updateManager(beanMappingService.mapTo(m, Manager.class));
	}

	@Override
	public void deleteManager(Long id) {
		managerService.deleteManager(new Manager(id));
	}

	@Override
	public ManagerDto getManagerById(Long managerId) {
		Manager m = managerService.getManagerById(managerId);
		if (m == null)
			return null;
		return beanMappingService.mapTo(m, ManagerDto.class);
	}

	@Override
	public List<TeamDto> getTeamsOfManager(Long managerId) {
		Manager m = managerService.getManagerById(managerId);
		if (m == null)
			return null;

		List<Team> teams = m.getTeams();
		return beanMappingService.mapTo(teams, TeamDto.class);
	}

	@Override
	public ManagerDto getManagerByEmail(String email) {
		Manager m = managerService.getManagerByEmail(email);
		if (m == null)
			return null;
		return beanMappingService.mapTo(m, ManagerDto.class);
	}

	@Override
	public ManagerDto getManagerByClubName(String clubName) {
		Manager c = managerService.getManagerByClubName(clubName);
		if (c == null)
			return null;
		return beanMappingService.mapTo(c, ManagerDto.class);
	}

	@Override
	public List<ManagerDto> getAllManagers() {
		return beanMappingService.mapTo(managerService.getAllManagers(), ManagerDto.class);
	}

	@Override
	public List<PlayerDto> getFreePlayers(Long managerId) {
		return beanMappingService.mapTo(managerService.getFreePlayersOfClub(new Manager(managerId)),
				PlayerDto.class);
	}

	public boolean authenticateManager(ManagerAuthenticationDto m) {
		//TODO
                return false;
	}

}
