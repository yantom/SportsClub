package cz.muni.fi.pa165.sportsClub.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Jan Tomasek
 */
@Entity
public class Manager {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	@Column(nullable=false,unique=true)
	private String email;

	private String mobile;

	@Column(nullable = false)
	private String password;

	@OneToOne(mappedBy="manager")
	@NotNull
	private Club club;

	@OneToMany
	private Set<Team> teams = new HashSet<>();

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

	public Set<Team> getTeams() {
		return teams;
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
}