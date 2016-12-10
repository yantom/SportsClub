package cz.muni.fi.pa165.sportsClub.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;

@Service
public class ClubServiceImpl implements ClubService {
	
	@Inject
	private ClubDao clubDao;


	public void createClub(Club c) {
        try {
            clubDao.createClub(c);
        } catch (Exception e) {
            throw new DaoLayerException("can not create club", e);
        }

	}

	public void updateClub(Club c) {
        try {
            clubDao.updateClub(c);
        } catch (Exception e) {
            throw new DaoLayerException("can not update club", e);
        }

	}

	public void deleteClub(Club c) {
        try {
            clubDao.deleteClub(c);
        } catch (Exception e) {
            throw new DaoLayerException("can not update club", e);
        }
	}

	public Club getClubById(Long clubId) {
        try {
            return clubDao.getClubById(clubId);
        } catch (Exception e) {
            throw new DaoLayerException("can not find club by id", e);
        }
	}

	public Club getClubByName(String clubName) {
        try {
            return clubDao.getClubByName(clubName);
        } catch (Exception e) {
            throw new DaoLayerException("can not find club by name", e);
        }
	}

	public void assignManagerToClub(Manager m, Club c) {
        try {
            Club club = clubDao.getClubById(c.getId());
                    club.setManager(m);
		    m.setClub(club);
                    clubDao.updateClub(club);
        } catch (Exception e) {
            throw new DaoLayerException("can not assign manager to", e);
        }
	}

	public List<Player> getFreePlayersOfClub(Club c) {

        try {
            c = clubDao.getWholeClubById(c.getId());
            List<Player> players = c.getManager().getPlayers();
            List<Player> freePlayers = new ArrayList<Player>();
            for (Player player : players){
                if (player.getPlayerInfos().isEmpty()){
                    freePlayers.add(player);
                }
            }
            return freePlayers;
        } catch (Exception e) {
            throw new DaoLayerException("can not obtain free players of club", e);
        }
	}

	public List<Club> getAllClubs() {
		try{
            return clubDao.getAllClubs();
		} catch (Exception e) {
            throw new DaoLayerException("can not obtain all clubs", e);
        }
	}
}
