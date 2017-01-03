package cz.muni.fi.pa165.sportsClub.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Inject
	private ManagerDao managerDao;

	@Override
	public Manager createManager(Manager m) {
		m.setPassword(AuthUtils.computeHash(m.getPassword()));
		try {
			managerDao.createManager(m);
			return m;
		} catch (Exception e) {
			throw new DaoLayerException("can not create manager", e);
		}
	}

	@Override
	public void updateManager(Manager m) {
		try {
			managerDao.updateManager(m);
		} catch (Exception e) {
			throw new DaoLayerException("can not update manager", e);
		}
	}

	@Override
	public void deleteManager(Manager m) {
		try {
			managerDao.deleteManager(m);
		} catch (Exception e) {
			throw new DaoLayerException("can not delete manager", e);
		}
	}

	@Override
	public Manager getManagerById(Long managerId) {
		try {
			return managerDao.getManagerById(managerId);
		} catch (Exception e) {
			throw new DaoLayerException("can not find manager by id", e);
		}
	}

	@Override
	public Manager getManagerByEmail(String email) {
		try {
			return managerDao.getManagerByEmail(email);
		} catch (Exception e) {
			throw new DaoLayerException("can not find manager by email", e);
		}
	}

	@Override
	public Manager getManagerByClubName(String clubName) {
		try {
			return managerDao.getManagerByClubName(clubName);
		} catch (Exception e) {
			throw new DaoLayerException("can not find manager by clubName", e);
		}
	}

	@Override
	public List<Player> getFreePlayersOfClub(Manager m) {
		try {
			return managerDao.getFreePlayers(m);
		} catch (Exception e) {
			throw new DaoLayerException("can not obtain free players of manager", e);
		}
	}

	@Override
	public List<Manager> getAllManagers() {
		try {
			return managerDao.getAllManagers();
		} catch (Exception e) {
			throw new DaoLayerException("can not obtain all managers", e);
		}
	}

	@Override
	public List<Team> getNotCreatedTeamsOfManager(Manager m) {
		try {
			List<Category> categoriesOfCreatedTeams =  managerDao.getCategoriesOfTeams(m);
			List<Category> allCategories = new ArrayList(Arrays.asList(Category.values()));
			allCategories.removeAll(categoriesOfCreatedTeams);
			List<Team>notAlreadyCreatedTeams = new ArrayList<>();
			for(Category category : allCategories){
				Team team = new Team();
				team.setCategory(category);
				team.setManager(m);
				notAlreadyCreatedTeams.add(team);
			}
			return notAlreadyCreatedTeams;
		} catch (Exception e) {
			throw new DaoLayerException("can not obtain not created teams of manager", e);
		}
	}

	@Override
	public Manager authenticate(String email, String password) {
		Manager manager = null;
		try {
			manager = managerDao.getManagerByEmail(email);
		} catch (Exception e) {
			throw new DaoLayerException("can not obtain all managers", e);
		}
		if (manager == null) {
			// authentication failed - wrong email
			return null;
		}
		String accessingUserHash = AuthUtils.computeHash(password);
		if (!accessingUserHash.equals(manager.getPassword())) {
			throw new IllegalArgumentException(accessingUserHash + " not equal with " + manager.getPassword());
			// return null;
		}
		return manager;
	}
}
