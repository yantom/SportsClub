package cz.muni.fi.pa165.sportsClub.service;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;

public class ManagerServiceImpl implements ManagerService {
	
        @Inject
	ManagerDao managerDao;

	public void createManager(Manager m) {
		managerDao.createManager(m);

	}

	public void updateManager(Manager m) {
		managerDao.updateManager(m);

	}

	public void deleteManager(Manager m) {
		managerDao.deleteManager(m);
	}

	public Manager getManagerById(Long managerId) {
		return managerDao.getManagerById(managerId);
	}

	public Manager getManagerByEmail(String email) {
		return managerDao.getManagerByEmail(email);
	}
}
