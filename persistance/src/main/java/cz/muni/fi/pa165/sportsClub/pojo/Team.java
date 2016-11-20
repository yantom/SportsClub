package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.ArrayList;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy="team", cascade = CascadeType.ALL)
    private List<PlayerInfo> playerInfos;
    
    @NotNull
    private Category category;
    
	@ManyToOne
	@NotNull
    private Manager manager;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
	}
    
    public List<PlayerInfo> getPlayerInfos() {
		return playerInfos = new ArrayList<>();
    }

    public void setPlayerInfos(List<PlayerInfo> playerInfo) {
        this.playerInfos = playerInfo;
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
        return getManager().getClub().getName() + " - " + getCategory().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;
		if (!getCategory().equals(team.getCategory()))
			return false;
		if (getManager() == null || team.getManager() == null)
			return true;
		return getManager().getEmail().equals(team.getManager().getEmail());

    }

    @Override
    public int hashCode() {
		return 31 * getCategory().hashCode() + ((getManager() == null) ? 0 : getManager().getEmail().hashCode());
    }
}
