package cz.muni.fi.pa165.sportsClub.service.facade;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ManagerAuthenticationDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.facade.ManagerFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.service.ManagerService;

@Transactional
@Service
public class ManagerFacadeImpl implements ManagerFacade {

    @Inject
	ManagerService managerService;

	public void updateManager(ManagerDto m) {
		managerService.updateManager(new ModelMapper().map(m, Manager.class));
	}

	public void deleteManager(ManagerDto m) {
		managerService.deleteManager(new ModelMapper().map(m, Manager.class));
	}

	public ManagerDto getManagerById(Long managerId) {
		Manager m = managerService.getManagerById(managerId);
		if (m == null)
			return null;
		return new ModelMapper().map(m, ManagerDto.class);
	}

	public ManagerDto getManagerByEmail(String email) {
		Manager m = managerService.getManagerByEmail(email);
		if (m == null)
			return null;
		return new ModelMapper().map(m, ManagerDto.class);
	}

	public boolean authenticateManager(ManagerAuthenticationDto m) {
		//TODO
                return false;
	}

}
