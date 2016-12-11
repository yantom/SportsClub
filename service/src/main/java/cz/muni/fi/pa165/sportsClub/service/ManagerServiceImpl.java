package cz.muni.fi.pa165.sportsClub.service;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Inject
	private ManagerDao managerDao;

        /**
	public void createManager(Manager m) {
		managerDao.createManager(m);
	}
        */
    public void updateManager(Manager m) {
		try {
			managerDao.updateManager(m);
		} catch (Exception e) {
			throw new DaoLayerException("can not update manager", e);
		}
	}

	public void deleteManager(Manager m) {
		try {
			managerDao.deleteManager(m);
		} catch (Exception e) {
			throw new DaoLayerException("can not delete manager", e);
		}
	}

	public Manager getManagerById(Long managerId) {
		try {
			return managerDao.getManagerById(managerId);
		} catch (Exception e) {
			throw new DaoLayerException("can not find manager by id", e);
		}
	}

	public Manager getManagerByEmail(String email) {
		try {
			return managerDao.getManagerByEmail(email);
		} catch (Exception e) {
			throw new DaoLayerException("can not find manager by email", e);
		}
	}
}
