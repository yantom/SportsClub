package cz.muni.fi.pa165.sportsClub.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;

@Service
public class ClubServiceImpl implements ClubService {
	
        @Inject
	private ClubDao clubDao;
        
        @Inject
        private PlayerDao playerDao;
        
        @Inject
        private PlayerService playerService;

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
		m.setClub(club);
                clubDao.updateClub(club);
	}

	public List<Player> getFreePlayersOfClub(Club c) {
		c = clubDao.getWholeClubById(c.getId());
		List<Player> players = c.getManager().getPlayers();
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
