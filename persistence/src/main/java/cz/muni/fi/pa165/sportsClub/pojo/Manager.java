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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @author Jan Tomasek
 */
@Entity
public class Manager {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Length(max = 32, min = 2)
	private String firstName;

	@Column(nullable = false)
	@Length(max = 32, min = 2)
	private String lastName;

	@Column(nullable = false, unique = true)
	@Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
	private String email;

	@Pattern(regexp = "(\\+|00)?\\d+")
	@Column(unique = true)
	private String mobile;

	@Column(nullable = false)
	private String password;

	@OneToOne(mappedBy="manager",cascade = CascadeType.ALL)
	@NotNull
	private Club club;

	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	private List<Team> teams = new ArrayList<>();

	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	private List<Player> players = new ArrayList<>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
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
		int result = 1;
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Manager))
			return false;
		Manager other = (Manager) obj;
		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + ", password=" + password + ", club=" + club + "]";
	}

}