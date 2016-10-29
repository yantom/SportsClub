package cz.muni.fi.pa165.sportsClub.dao;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 * 
 * 
 * @author Andrej 410433
 */
public interface TeamDao {
    
    /**
     * Creates a new team
     * 
     * @param team
     */
    void createTeam(Team team);
    
    /**
     * Updates team
     * 
     * @param team 
     */
    void updateTeam(Team team);
    
    /**
     * Deletes team
     * 
     * @param team 
     */
    void deleteTeam(Team team);
    
    /**
     * Finds team by its id
     * 
     * @param id 
     * @return found team
     */
    Team getTeamById(Long id);
}
