package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.sportsClub.enums.Category;

/**
 * Respresents team with specific age category
 *
 * @author Andrej 410433
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@OneToMany(mappedBy = "team", cascade = { CascadeType.REMOVE })
    private List<PlayerInfo> playerInfos = new ArrayList<>();

    @NotNull
    private Category category;

    @ManyToOne
    @NotNull
	private Club club;

	public Club getClub() {
		return club;
    }

	public void setClub(Club club) {
		this.club = club;
    }

    /**
     * Sets list of player infos
     *
     * @param playerInfo player infos of team
     */
    public void setPlayerInfos(List<PlayerInfo> playerInfo) {
        this.playerInfos = playerInfo;
    }

    /**
     * Gets list of player infos
     *
     * @return all player infos of team
     */
    public List<PlayerInfo> getPlayerInfos() {
        return Collections.unmodifiableList(playerInfos);
    }

	public void addPlayerInfo(PlayerInfo pi) {
		playerInfos.add(pi);
    }

	public void removePlayerInfo(PlayerInfo pi) {
		playerInfos.remove(pi);
    }

	public void updatePlayerInfo(PlayerInfo pi) {
		playerInfos.remove(pi);
		playerInfos.add(pi);
    }

    /**
     * Gets age category of team
     *
     * @return age category of team
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets age category of tema
     *
     * @param category age category of team
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets id of team
     *
     * @return id of team
     *
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id of team
     *
     * @param id of team
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
		return getClub().getName() + " - " + getCategory().toString() + ", id=" + getId();
    }

	public String toInsertStatement() {
		return "INSERT INTO Team (id,category,club_id) VALUES (" + getId() + "," + getCategory().ordinal() + ","
				+ getClub().getId() + ");" + System.lineSeparator();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Team)) {
            return false;
        }

        Team team = (Team) o;
        if (!getCategory().equals(team.getCategory())) {
            return false;
        }
		if (getClub() == null || team.getClub() == null) {
            return true;
        }
		return getClub().getName().equals(team.getClub().getName());

    }

    @Override
    public int hashCode() {
		return 31 * getCategory().hashCode() + ((getClub() == null) ? 0 : getClub().getName().hashCode());
    }
}
