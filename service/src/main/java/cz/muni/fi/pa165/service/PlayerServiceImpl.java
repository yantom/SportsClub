package cz.muni.fi.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;

public class PlayerServiceImpl implements PlayerService {
	@Inject
	PlayerDao playerDao;

	public void createPlayer(Player p) {
		// TODO Auto-generated method stub

	}

	public void updatePlayer(Player p) {
		// TODO Auto-generated method stub

	}

	public void deletePlayer(Player p) {
		// TODO Auto-generated method stub

	}

	public Player getPlayerById(Long playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Player getPlayerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Player> getAllPlayersOfClub(Club c) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PlayerInfo> getPlayerInfos(Player p) {
		// TODO Auto-generated method stub
		return null;
	}
}
