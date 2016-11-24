package cz.muni.fi.pa165.sportsClub.facade;

import cz.muni.fi.pa165.sportsClub.dto.ManagerAuthenticationDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;

public interface ManagerFacade {
	void createManager(ManagerDto m);

	void updateManager(ManagerDto m);

	void deleteManager(ManagerDto m);

	ManagerDto getManagerById(Long managerId);

	ManagerDto getManagerByEmail(String email);

	boolean authenticateManager(ManagerAuthenticationDto m);
}
