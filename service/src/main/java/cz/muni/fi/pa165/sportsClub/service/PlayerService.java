package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

public interface PlayerService {
	void createPlayer(Player p);

	void updatePlayer(Player p);

	void deletePlayer(Player p);

	Player getPlayerById(Long playerId);

	Player getPlayerByEmail(String email);

	List<Player> getAllPlayersOfClub(Manager m);

	List<PlayerInfo> getPlayerInfos(Long playerId);


}
