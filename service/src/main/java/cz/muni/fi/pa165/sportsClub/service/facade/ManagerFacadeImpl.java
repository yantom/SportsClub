package cz.muni.fi.pa165.sportsClub.service.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ManagerAuthenticationDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.facade.ManagerFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.service.ManagerService;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;

@Transactional
@Service
public class ManagerFacadeImpl implements ManagerFacade {

    @Inject
	ManagerService managerService;
        
        /**
	public void createManager(ManagerDto m) {
		managerService.createManager(new ModelMapper().map(m, Manager.class));

	}
        */
    
    
	public void updateManager(ManagerDto m) {
		managerService.updateManager(new ModelMapper().map(m, Manager.class));

	}

	public void deleteManager(ManagerDto m) {
		managerService.deleteManager(new ModelMapper().map(m, Manager.class));

	}

	public ManagerDto getManagerById(Long managerId) {
		return new ModelMapper().map(managerService.getManagerById(managerId), ManagerDto.class);
	}

	public ManagerDto getManagerByEmail(String email) {
		return new ModelMapper().map(managerService.getManagerByEmail(email), ManagerDto.class);
	}

	public boolean authenticateManager(ManagerAuthenticationDto m) {
		//TODO
                return false;
	}

}
