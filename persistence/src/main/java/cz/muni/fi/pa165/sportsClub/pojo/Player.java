package cz.muni.fi.pa165.sportsClub.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    @Min(75)
    @Max(250)
    private double height;

    @NotNull
    @Column(nullable = false)
    @Min(20)
    @Max(150)
    private double weight;

    @NotNull
    @Column(nullable = false)
    @Past
    private LocalDate dateOfBirth;
    
    @NotNull
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
    private String email;

    @Column(unique = true)
    @Pattern(regexp = "(\\+|00)?\\d+")
    private String mobile;

    @ManyToOne
    @NotNull
	private Club club;

	@OneToMany(mappedBy = "player", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
    private List<PlayerInfo> playerInfos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

	public Club getClub() {
		return club;
    }

	public void setClub(Club club) {
		this.club = club;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        if (getEmail() == null) {
            if (other.getEmail() != null) {
                return false;
            }
        } else if (!getEmail().equals(other.getEmail())) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Player [id=" + id + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", managerId="
				+ club.getId() + "]";
	}

	public String toInsertStatement() {
		return "INSERT INTO Player (id,firstName,lastName,email,mobile,weight,height,dateOfBirth,club_id) VALUES ("
				+ getId() + "," + DBEntityUtils.quote(getFirstName()) + "," + DBEntityUtils.quote(getLastName()) + ","
				+ DBEntityUtils.quote(getEmail()) + "," + DBEntityUtils.quote(getMobile()) + "," + getWeight() + "," + getHeight() + ","
				+ DBEntityUtils.dateToVarBinary(getDateOfBirth()) + "," +
				+ getClub().getId()
				+ ");"
				+ System.lineSeparator();
	}

}
