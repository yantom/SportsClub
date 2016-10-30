package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents player with specific jersey number in given team
 * 
 * @author Andrej 410433
 */

@Entity
@IdClass(PlayerInfoId.class)
public class PlayerInfo {

    @Id
    private long playerId;

    @Id
    private long teamId;

    @NotNull
    private int jerseyNumber;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="PLAYERID", referencedColumnName="ID")
    private Player player;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="TEAMID", referencedColumnName="ID")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
  
    /**
     * Gets player
     * 
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player
     * 
     * @param player
     */
    public void setPlayer(Player player) {
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
    public String toString() {return "#" + jerseyNumber + ", " + player + ", " + team; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerInfo)) return false;

        PlayerInfo that = (PlayerInfo) o;

        if (playerId != that.playerId) return false;
        return teamId == that.teamId;

    }

    @Override
    public int hashCode() {
        int result = (int) (playerId ^ (playerId >>> 32));
        result = 31 * result + (int) (teamId ^ (teamId >>> 32));
        return result;
    }
}
