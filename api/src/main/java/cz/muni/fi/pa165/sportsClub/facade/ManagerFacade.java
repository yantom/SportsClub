package cz.muni.fi.pa165.sportsClub.facade;

import cz.muni.fi.pa165.sportsClub.dto.ManagerAuthenticationDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;

public interface ManagerFacade {
	void updateManager(ManagerDto m);

	void deleteManager(Long id);

	ManagerDto getManagerById(Long managerId);

	ManagerDto getManagerByEmail(String email);

	boolean authenticateManager(ManagerAuthenticationDto m);
}
