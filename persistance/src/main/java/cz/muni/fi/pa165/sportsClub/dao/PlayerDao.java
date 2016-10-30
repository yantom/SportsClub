package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Player;

/**
 * Database operations interface for {@link Player}
 *
 * @author Simon Sudora 461460
 */

public interface PlayerDao {

    /**
     * Stores player object into database.
     *
     * @param player who is to be stored
     */
    void createPlayer(Player player);

    /**
     * Update existing player object in database.
     *
     * @param player who is to be update
     */
    void updatePlayer(Player player);

    /**
     * Delete existing player object from database.
     *
     * @param player who is to be deleted
     */
    void deletePlayer(Player player);

    /**
     * Retrieves player object by its unique id.
     *
     * @param id of player
     * @return player object with specified id if exists, null otherwise
     */
    Player getPlayerById(Long id);
}
