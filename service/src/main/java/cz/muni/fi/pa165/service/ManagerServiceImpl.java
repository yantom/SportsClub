package cz.muni.fi.pa165.service;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;

public class ManagerServiceImpl implements ManagerService {
	@Inject
	ManagerDao managerDao;

	public void createManager(Manager m) {
		// TODO Auto-generated method stub

	}

	public void updateManager(Manager m) {
		// TODO Auto-generated method stub

	}

	public void deleteManager(Manager m) {
		// TODO Auto-generated method stub

	}

	public Manager getManagerById(Long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Manager getManagerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
