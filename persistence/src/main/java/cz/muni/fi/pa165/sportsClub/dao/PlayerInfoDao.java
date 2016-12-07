package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;

/**
 * CRUD operations on PlayerInfo
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
     * Finds player info by its id
     * 
     * @param piId player info id
     * @return found player info
     */
	PlayerInfo getPlayerInfoById(Long pid);
}

