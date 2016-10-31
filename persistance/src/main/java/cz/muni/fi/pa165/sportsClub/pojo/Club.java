package cz.muni.fi.pa165.sportsClub.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Koncak (410155)
 */

@Entity
public class Club {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @NotNull
    private Manager manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = prime * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Club)){ 
            return false;
        }
        final Club other = (Club) obj;
        if (getId() != other.getId() && (getId() == null || !getId().equals(other.getId()))) {
            return false;
        }
        if ((getName() == null) ? (other.getName() != null) : !getName().equals(other.getName())) {
            return false;
        }
        return true;
    }
}
