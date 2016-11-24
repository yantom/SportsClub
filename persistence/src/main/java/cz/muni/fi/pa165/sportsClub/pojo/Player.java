package cz.muni.fi.pa165.sportsClub.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import cz.muni.fi.pa165.sportsClub.pojo.validation.Past;

/**
 * @author Simon Sudora 461460
 */

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false)
	@Min(75)
	@Max(250)
    private double height;

    @Column(nullable=false)
	@Min(20)
	@Max(150)
    private double weight;

    @Column(nullable=false)
	@Past
    private LocalDate dateOfBirth;

	@Column(nullable = false, unique = true)
	@Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
    private String email;

	@Column(unique = true)
	@Pattern(regexp = "(\\+|00)?\\d+")
    private String mobile;

	@ManyToOne
	@NotNull
	private Manager manager;

    @OneToMany(mappedBy="player", cascade = CascadeType.ALL)
	private List<PlayerInfo> playerInfos = new ArrayList<>();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getHeight() { return height; }

    public void setHeight(double height) { this.height = height; }

    public double getWeight() { return weight; }

    public void setWeight(double weight) { this.weight = weight; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
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
		if (!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;
		return true;
	}
}
