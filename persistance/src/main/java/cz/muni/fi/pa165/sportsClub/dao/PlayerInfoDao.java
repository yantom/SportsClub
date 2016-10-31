package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import java.util.List;

/**
 *
 * @author Andrej 410433
 */
public interface PlayerInfoDao {
    /**
     * Creates a new player info and stores into database
     * 
     * @param pi
     */
    void createPlayerInfo(PlayerInfo pi);
    
    /**
     * Updates exist player info in database
     * 
     * @param pi
     */
    void updatePlayerInfo(PlayerInfo pi);
    
    /**
     * Deletes player info in database
     * 
     * @param pi
     */
    void deletePlayerInfo(PlayerInfo pi);
    
    /**
     * Finds player info by its team id
     * 
     * @param teamId id of team in which is player info
     * @return found player info
     */
    PlayerInfo getPlayerInfoByTeamId(Long teamId);
    
    /**
     * Finds player info by its player id
     * 
     * @param playerId id of player in which is player info
     * @return found player info
     */
    PlayerInfo getPlayerInfoByPlayerId(Long playerId);
}

