package cz.muni.fi.pa165.sportsClub.dao;

import java.time.LocalDate;
import java.util.List;

import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;

/**
 *
 * @author David Koncak (410155)
 */
public interface ClubDao {
    
    /**
     * Deletes club
     * 
     * @param club
     */
    void deleteClub(Club club);
    
    /**
     * Creates a club
     * 
     * @param club
     */
    void createClub(Club club);
    
    /**
     * Finds club by id
     * 
     * @param id
     * @return Club
     */
    Club getClubById(Long id);
    
    /**
     * Updates club
     * 
     * @param club
     */
    void updateClub(Club club);
    
    Club getClubByName(String name);
    
    List<Club> getAllClubs();
    
	/**
	 * 
	 * @param clubId
	 * @return completly initialized club with all data
	 *         (players,teams,associations)
	 */
	Club getWholeClubById(Long clubId);

    /**
     *
     * @param club
     * @return all the free players of the club
     */
    List<Player> getFreePlayers(Club club);

    /**
     *
     * @param club,ageLimitDate
     * @return list of players with higher date of birth than certain date
     */
    List<Player> getPlayersWithHigherDobThan(Club club, LocalDate ageLimitDate);

}
