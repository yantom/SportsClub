package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Max(99)
    @Min(0)
    private int jerseyNumber;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "PLAYERID", referencedColumnName = "ID")
    private Player player;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "TEAMID", referencedColumnName = "ID")
    private Team team;

    /**
     * Gets team
     * 
     * @return team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets team
     * 
     * @param team 
     */
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

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
		return "#" + jerseyNumber + ", " + player + ", " + team + ", " + playerId + ", " + teamId;
    }
    
        @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.getJerseyNumber();
        hash = 79 * hash + Objects.hashCode(this.getPlayer());
        hash = 79 * hash + Objects.hashCode(this.getTeam());
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
        if (this.getJerseyNumber() != other.getJerseyNumber()) {
            return false;
        }
        if (!Objects.equals(this.getPlayer(), other.getPlayer())) {
            return false;
        }
        if (!Objects.equals(this.getTeam(), other.getTeam())) {
            return false;
        }
        return true;
    }
}
