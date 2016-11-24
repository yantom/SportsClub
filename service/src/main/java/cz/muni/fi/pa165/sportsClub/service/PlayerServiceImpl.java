package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;

/**
 * Represents service layer of player
 * 
 * @author Andrej Bonis 410433
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Inject
    PlayerDao playerDao;
    
    @Inject
    ClubDao clubDao;

    public void createPlayer(Player p) {
        playerDao.createPlayer(p);

    }

    public void updatePlayer(Player p) {
        playerDao.updatePlayer(p);

    }

    public void deletePlayer(Player p) {
        playerDao.deletePlayer(p);

    }

    public Player getPlayerById(Long playerId) {
        return playerDao.getPlayerById(playerId);
    }

    public Player getPlayerByEmail(String email) {
        return playerDao.getPlayerByEmail(email);
    }

    public List<Player> getAllPlayersOfClub(Club c) {
        Club club = clubDao.getClubById(c.getId());
        return club.getManager().getPlayers();

    }

    public List<PlayerInfo> getPlayerInfos(Player p) {
        Player player = playerDao.getPlayerById(p.getId());
        return player.getPlayerInfos();
    }
}
