package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Koncak (410155)
 */
@Entity
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @PrimaryKeyJoinColumn
    private Manager manager;

	@OneToMany(mappedBy = "club", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Team> teams = new ArrayList<>();

	@OneToMany(mappedBy = "club", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Player> players = new ArrayList<>();

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

	public List<Team> getTeams() {
		return Collections.unmodifiableList(teams);
	}

	public void addTeam(Team team) {
		teams.add(team);
	}

	public void removeTeam(Team team) {
		teams.remove(team);
	}

	public void updateTeam(Team team) {
		teams.remove(team);
		teams.add(team);
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void removePlayer(Player player) {
		players.remove(player);
	}

	public void updatePlayer(Player player) {
		players.remove(player);
		players.add(player);
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Club)) {
            return false;
        }
        final Club other = (Club) obj;
        if ((getName() == null) ? (other.getName() != null) : !getName().equals(other.getName())) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + ", managerId=" + manager.getId() + ", managerEmail="
				+ manager.getEmail() + "]";
	}

}
