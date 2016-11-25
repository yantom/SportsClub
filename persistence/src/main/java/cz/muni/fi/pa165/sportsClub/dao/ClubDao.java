package cz.muni.fi.pa165.sportsClub.dao;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.pojo.Club;

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

}
