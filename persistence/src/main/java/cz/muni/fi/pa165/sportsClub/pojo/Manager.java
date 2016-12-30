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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author David Koncak (410155)
 */
@Entity
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
	private String clubName;

	@OneToMany(mappedBy = "manager", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Team> teams = new ArrayList<>();

	@OneToMany(mappedBy = "manager", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Player> players = new ArrayList<>();

	@NotNull
	@Column(nullable = false)
	@Size(max = 32, min = 2)
	private String firstName;

	@NotNull
	@Column(nullable = false)
	@Size(max = 32, min = 2)
	private String lastName;

	@NotNull
	@Column(nullable = false, unique = true)
	@Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
	private String email;

	@Pattern(regexp = "(\\+|00)?\\d+")
	@Column(unique = true)
	private String mobile;

	@NotNull
	@Column(nullable = false)
	private String password;

	@NotNull
	@Column(nullable = false)
	private String role;

	public Manager() {
	}

	public Manager(Long id) {
		this.id = id;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getClubName() {
		return clubName;
    }

	public void setClubName(String name) {
		this.clubName = name;
    }

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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
		hash = prime * hash + (this.getEmail() != null ? this.getEmail().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Manager)) {
            return false;
        }
        final Manager other = (Manager) obj;
		if ((getEmail() == null) ? (other.getEmail() != null) : !getEmail().equals(other.getEmail())) {
            return false;
        }
        return true;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + ", password=" + password + ", clubName=" + clubName + "]";
	}

	public String toInsertStatement() {
		return "INSERT INTO Manager (id,firstName,lastName,email,mobile,password,clubName,role) VALUES (" + getId()
				+ ","
				+ DBEntityUtils.quote(getFirstName()) + "," + DBEntityUtils.quote(getLastName()) + ","
				+ DBEntityUtils.quote(getEmail()) + "," + DBEntityUtils.quote(getMobile()) + ","
				+ DBEntityUtils.quote(getPassword()) + "," + DBEntityUtils.quote(getClubName()) + ","
				+ DBEntityUtils.quote(getRole()) + ");"
				+ System.lineSeparator();
	}
}
