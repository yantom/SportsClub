package cz.muni.fi.pa165.sportsClub.service.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ManagerAuthenticationDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.facade.ManagerFacade;

@Transactional
@Service
public class ManagerFacadeImpl implements ManagerFacade {

	public void createManager(ManagerDto m) {
		// TODO Auto-generated method stub

	}

	public void updateManager(ManagerDto m) {
		// TODO Auto-generated method stub

	}

	public void deleteManager(ManagerDto m) {
		// TODO Auto-generated method stub

	}

	public ManagerDto getManagerById(Long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ManagerDto getManagerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean authenticateManager(ManagerAuthenticationDto m) {
		// TODO Auto-generated method stub
		return false;
	}

}
