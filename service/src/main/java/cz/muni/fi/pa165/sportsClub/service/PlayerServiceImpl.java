package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PlayerDao playerDao;
    
    @Inject
    private ClubDao clubDao;

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
		List<Player> players = clubDao.getClubById(c.getId()).getManager().getPlayers();
		players.size();
		return players;
    }

    public List<PlayerInfo> getPlayerInfos(Player p) {
		List<PlayerInfo> playerInfos = playerDao.getPlayerById(p.getId()).getPlayerInfos();
		playerInfos.size();
		return playerInfos;
    }
}
