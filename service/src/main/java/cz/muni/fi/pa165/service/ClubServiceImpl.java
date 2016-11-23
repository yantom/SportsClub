package cz.muni.fi.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class ClubServiceImpl implements ClubService {
	
        @Inject
	ClubDao clubDao;
        
        @Inject
        PlayerDao playerDao;

	public void createClub(Club c) {
		clubDao.createClub(c);

	}

	public void updateClub(Club c) {
		clubDao.updateClub(c);

	}

	public void deleteClub(Club c) {
		clubDao.deleteClub(c);

	}

	public Club getClubById(Long clubId) {
		return clubDao.getClubById(clubId);
	}

	public Club getClubByName(String clubName) {
		return clubDao.getClubByName(clubName);
	}

	public void assignManagerToClub(Manager m, Club c) {
		Club club = clubDao.getClubById(c.getId());
                club.setManager(m);
                clubDao.updateClub(club);

	}

	public List<Player> getFreePlayers() {
		List<Player> players = playerDao.getAllPlayers();
                List<Player> freePlayers = new ArrayList<Player>();
		for (Player player : players){
                    if (player.getPlayerInfos().isEmpty()){
                        freePlayers.add(player);
                    }
                }
                return freePlayers;
	}

	public List<Club> getAllClubs() {
		return clubDao.getAllClubs();
	}
}
