package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Club;

/**
 *
 * @author David Koncak (410155)
 */
public interface ClubDao {
    
    void deleteClub(Club club);
    
    void createClub(Club club);
    
    Club getClubById(Long id);
    
    void updateClub(Club club);
    
}
