package cz.muni.fi.pa165.sportsClub.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import java.util.List;

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

    @OneToMany(mappedBy="team")
    private List<PlayerInfo> playerInfos;
    
    @NotNull
    private Category category;
    
    
    public List<PlayerInfo> getPlayerInfo() {
        return playerInfos;
    }

    public void setPlayerInfo(List<PlayerInfo> playerInfo) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        return getId().equals(team.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
