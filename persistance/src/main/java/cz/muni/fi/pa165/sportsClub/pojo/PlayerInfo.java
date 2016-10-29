package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 * Represents player with specific jersey number in given team
 * 
 * @author Andrej 410433
 */
public class PlayerInfo {

    @NotNull
    private int jerseyNumber;
    @NotNull
    private PlayerPojo player;
    @ManyToMany
    private List<Team> teams = new ArrayList<Team>();

    
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
  
    /**
     * Gets player
     * 
     * @return player
     */
    public PlayerPojo getPlayer() {
        return player;
    }

    /**
     * Sets player
     * 
     * @param player
     */
    public void setPlayer(PlayerPojo player) {
        this.player = player;
    }

    /**
     * Gets jersey number of player
     * 
     * @return jersey number of player
     */
    public int getJerseyNumber() {
        return this.jerseyNumber;
    }

    
    /**
     * Sets jersey number of player
     * 
     * @param jerseyNumber jersey number of player
     */
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
   
    @Override
    public String toString() {
        return "#" + jerseyNumber + ", " + player;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.jerseyNumber;
        hash = 83 * hash + (this.player != null ? this.player.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerInfo other = (PlayerInfo) obj;
        if (this.jerseyNumber != other.jerseyNumber) {
            return false;
        }
        if (this.player != other.player && (this.player == null || !this.player.equals(other.player))) {
            return false;
        }
        return true;
    }
    
    
}
