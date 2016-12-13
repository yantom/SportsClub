package cz.muni.fi.pa165.sportsClub.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class ClubDto {
	    private Long id;
	    @NotNull
	    private String name;
	    private ManagerDto manager;

	private List<TeamDto> teams = new ArrayList<>();

	private List<PlayerDto> players = new ArrayList<>();

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

	    public ManagerDto getManager() {
	        return manager;
	    }

	    public void setManager(ManagerDto manager) {
	        this.manager = manager;
	    }
	    
	public List<TeamDto> getTeams() {
		return teams;
	}

	public void addTeam(TeamDto team) {
		teams.add(team);
	}

	public void removeTeam(TeamDto team) {
		teams.remove(team);
	}

	public void updateTeam(TeamDto team) {
		teams.remove(team);
		teams.add(team);
	}

	public List<PlayerDto> getPlayers() {
		return players;
	}

	public void addPlayer(PlayerDto player) {
		players.add(player);
	}

	public void removePlayer(PlayerDto player) {
		players.remove(player);
	}

	public void updatePlayer(PlayerDto player) {
		players.remove(player);
		players.add(player);
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + "]";
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
	        if (!(obj instanceof ClubDto)){ 
	            return false;
	        }
	        final ClubDto other = (ClubDto) obj;
	        if ((getName() == null) ? (other.getName() != null) : !getName().equals(other.getName())) {
	            return false;
	        }
	        return true;
	    }

}
