package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;

public interface PlayerService {
	void createPlayer(Player p);

	void updatePlayer(Player p);

	void deletePlayer(Player p);

	Player getPlayerById(Long playerId);

	Player getPlayerByEmail(String email);

	List<Player> getAllPlayersOfClub(Club c);

	List<PlayerInfo> getPlayerInfos(Player p);
}
