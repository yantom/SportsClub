package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Represents player with specific jersey number in given team
 *
 * @author Andrej 410433
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "playerId", "teamId" }))
public class PlayerInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @NotNull
    @Max(99)
    @Min(0)
    private int jerseyNumber;

	@ManyToOne
	@JoinColumn(name = "playerId")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "teamId")
	private Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

    @Override
    public String toString() {
		return "#" + jerseyNumber + ", " + player + ", " + team;
    }
    
	public String toInsertStatement() {
		return "INSERT INTO PlayerInfo (id,jerseyNumber,playerId,teamId) VALUES (" + getId() + "," + getJerseyNumber()
				+ "," + getPlayer().getId() + "," + getTeam().getId() + ");" + System.lineSeparator();
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
