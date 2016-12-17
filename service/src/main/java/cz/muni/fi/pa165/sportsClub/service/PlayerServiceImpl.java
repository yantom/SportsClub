package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
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
	private ManagerDao managerDao;

	@Override
	public void createPlayer(Player p) {
        try {
            playerDao.createPlayer(p);
        } catch (Exception e) {
            throw new DaoLayerException("can not create player", e);
        }
    }

	@Override
	public void updatePlayer(Player p) {
        try {
            playerDao.updatePlayer(p);
        } catch (Exception e) {
            throw new DaoLayerException("can not update player", e);
        }
    }

	@Override
	public void deletePlayer(Player p) {
        try {
            playerDao.deletePlayer(p);
        } catch (Exception e) {
            throw new DaoLayerException("can not delete player", e);
        }
    }

	@Override
	public Player getPlayerById(Long playerId) {
        try {
            return playerDao.getPlayerById(playerId);
        } catch (Exception e) {
            throw new DaoLayerException("can not find player by id", e);
        }
    }

	@Override
	public Player getPlayerByEmail(String email) {
        try {
            return playerDao.getPlayerByEmail(email);
        } catch (Exception e) {
            throw new DaoLayerException("can not find player by email", e);
        }
    }

	@Override
	public List<Player> getAllPlayersOfClub(Manager m) {
        try {
			List<Player> players = managerDao.getManagerById(m.getId()).getPlayers();
            players.size();
            return players;
        } catch (Exception e) {
			throw new DaoLayerException("can not obtain all players of manager", e);
        }

    }

	@Override
	public List<PlayerInfo> getPlayerInfos(Player p) {
        try {
            List<PlayerInfo> playerInfos = playerDao.getPlayerById(p.getId()).getPlayerInfos();
            playerInfos.size();
            return playerInfos;
        } catch (Exception e) {
            throw new DaoLayerException("can not obtain player infos", e);
        }
    }
}
