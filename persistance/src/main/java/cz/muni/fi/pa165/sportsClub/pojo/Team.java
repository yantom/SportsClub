package cz.muni.fi.pa165.sportsClub.pojo;

import javax.validation.constraints.NotNull;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Respresents team with specific age category
 * 
 * @author Andrej 410433
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany(mappedBy="team")
    private List<PlayerInfo> players = new ArrayList<PlayerInfo>();
    
    @NotNull
    private Category category;
    
    
    public List<PlayerInfo> getPlayerInfo() {
        return players;
    }

    public void setPlayerInfo(List<PlayerInfo> playerInfo) {
        this.players = playerInfo;
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
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.category != null ? this.category.hashCode() : 0);
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
        if (!(obj instanceof Team)){ 
            return false;
        }
        final Team other = (Team) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.category != other.category) {
            return false;
        }
        return true;
    }
    
}
