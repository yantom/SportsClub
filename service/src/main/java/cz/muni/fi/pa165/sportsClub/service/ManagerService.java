package cz.muni.fi.pa165.sportsClub.service;

import cz.muni.fi.pa165.sportsClub.pojo.Manager;

public interface ManagerService {
	//void createManager(Manager m);

	void updateManager(Manager m);

	void deleteManager(Manager m);

	Manager getManagerById(Long managerId);

	Manager getManagerByEmail(String email);
}
