package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerOfTeamDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.facade.TeamFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import cz.muni.fi.pa165.sportsClub.service.TeamService;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

@Transactional
@Service
public class TeamFacadeImpl implements TeamFacade {
    
        @Inject
        private TeamService teamService;

	public void createTeam(TeamDto t) {
		teamService.createTeam(new ModelMapper().map(t, Team.class));

	}

	public void updateTeam(TeamDto t) {
		teamService.updateTeam(new ModelMapper().map(t, Team.class));

	}

	public void deleteTeam(TeamDto t) {
		teamService.deleteTeam(new ModelMapper().map(t, Team.class));

	}

	public TeamDto getTeamById(Long teamId) {
		return new ModelMapper().map(teamService.getTeamById(teamId), TeamDto.class);
	}

	public TeamDto getTeamOfClubByCategory(Category category, ClubDto c) {
		return new ModelMapper().map(teamService.getTeamOfClubByCategory(
                        category, new ModelMapper().map(c, Club.class)), TeamDto.class);
	}

	public List<TeamDto> getAllTeamsOfClub(ClubDto c) {
            return new ModelMapper().map(teamService.getAllTeamsOfClub(new ModelMapper().map(c, Club.class)), 
                    new TypeToken<List<TeamDto>>(){}.getType());
	}

	public List<PlayerOfTeamDto> getPlayersOfTeam(TeamDto t) {
		return null;
	}

	public void assignPlayerToTeam(PlayerDto p, TeamDto t, int jerseyNumber) {
		teamService.assignPlayerToTeam(new ModelMapper().map(p, Player.class),
                        new ModelMapper().map(t, Team.class), jerseyNumber);

	}

	public void changeJerseyNumber(PlayerDto p, TeamDto t, int jerseyNumber) {
		teamService.changeJerseyNumber(new ModelMapper().map(p, Player.class), 
                        new ModelMapper().map(t, Team.class), jerseyNumber);

	}

	public void removePlayerFromTeam(PlayerDto p, TeamDto t) {
		teamService.removePlayerFromTeam(new ModelMapper().map(p, Player.class),
                        new ModelMapper().map(t, Team.class));
	}
        
}
