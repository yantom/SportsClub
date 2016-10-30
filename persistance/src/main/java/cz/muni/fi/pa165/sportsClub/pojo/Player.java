package cz.muni.fi.pa165.sportsClub.pojo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private double height;

    @Column(nullable=false)
    private double weight;

    @Column(nullable=false)
    private LocalDate dateOfBirth;

    @Column(nullable=false)
    private String email;

    private String mobile;

    @OneToMany(mappedBy="player")
    private List<PlayerInfo> playerInfos;

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

    public List<PlayerInfo> getPlayerInfos() { return playerInfos; }

    public void setPlayerInfos(List<PlayerInfo> playerInfos) { this.playerInfos = playerInfos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (!getId().equals(player.getId())) return false;
        return getEmail().equals(player.getEmail());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
