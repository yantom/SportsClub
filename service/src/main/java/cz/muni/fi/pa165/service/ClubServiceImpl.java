package cz.muni.fi.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;

public class ClubServiceImpl implements ClubService {
	@Inject
	ClubDao clubDao;

	public void createClub(Club c) {
		// TODO Auto-generated method stub

	}

	public void updateClub(Club c) {
		// TODO Auto-generated method stub

	}

	public void deleteClub(Club c) {
		// TODO Auto-generated method stub

	}

	public Club getClubById(Long clubId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Club getClubByName(String clubName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void assignManagerToClub(Manager m) {
		// TODO Auto-generated method stub

	}

	public List<Player> getFreePlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Club> getAllClubs() {
		// TODO Auto-generated method stub
		return null;
	}
}
