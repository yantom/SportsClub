package cz.muni.fi.pa165.sportsClub.dao;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

/**
 * CRUD operations on Team
 * 
 * @author Andrej 410433
 */
public interface TeamDao {
    
    /**
     * Creates a new team and stores into database
     * 
     * @param team
     */
    void createTeam(Team team);
    
    /**
     * Updates exist team in database
     * 
     * @param team  
     */
    void updateTeam(Team team);
    
    /**
     * Deletes team in database
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

    /**
     * check if given jersey number already exist in the team
     *
     * @param team,jerseyNumber
     * @return if jersey number is unique
     */
    boolean isJerseyNumberUnique(Team team, int jerseyNumber);
}
