package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import cz.muni.fi.pa165.sportsClub.service.BeanMappingService;
import cz.muni.fi.pa165.sportsClub.service.TeamService;

@Transactional
@Service
public class TeamFacadeImpl implements TeamFacade {
    
	@Inject
	private TeamService teamService;

	@Inject
	private BeanMappingService beanMappingService;

	public void createTeam(TeamDto t) {
		teamService.createTeam(beanMappingService.mapTo(t, Team.class));

	}

	public void updateTeam(TeamDto t) {
		teamService.updateTeam(beanMappingService.mapTo(t, Team.class));

	}

	public void deleteTeam(TeamDto t) {
		teamService.deleteTeam(beanMappingService.mapTo(t, Team.class));

	}

	public TeamDto getTeamById(Long teamId) {
		Team t = teamService.getTeamById(teamId);
		return beanMappingService.mapTo(t, TeamDto.class);
	}

	public TeamDto getTeamOfClubByCategory(Category category, ClubDto c) {
		Team t = teamService.getTeamOfClubByCategory(category, beanMappingService.mapTo(c, Club.class));
		if (t == null)
			return null;
		return beanMappingService.mapTo(t, TeamDto.class);
	}

	public List<TeamDto> getAllTeamsOfClub(ClubDto c) {
		return beanMappingService.mapTo(teamService.getAllTeamsOfClub(beanMappingService.mapTo(c, Club.class)),
				TeamDto.class);
	}

	public List<PlayerOfTeamDto> getPlayersOfTeam(TeamDto t) {
		List<PlayerOfTeamDto> players = new ArrayList<PlayerOfTeamDto>();
		List<PlayerInfo> infos = teamService.getPlayerInfos(beanMappingService.mapTo(t, Team.class));
		PlayerOfTeamDto playerOfTeam;
		for (PlayerInfo playerInfo : infos) {
			playerOfTeam = new PlayerOfTeamDto();
			playerOfTeam.setJerseyNumber(playerInfo.getJerseyNumber());
			playerOfTeam.setPlayerOlderThanTeamLimit(playerInfo.isPlayerOlderThanTeamLimit());
			playerOfTeam.setPlayer(beanMappingService.mapTo(playerInfo.getPlayer(), PlayerDto.class));
			players.add(playerOfTeam);
		}
		return players;
	}

	public void assignPlayerToTeam(PlayerDto p, TeamDto t, int jerseyNumber) {
		teamService.assignPlayerToTeam(beanMappingService.mapTo(p, Player.class),
				beanMappingService.mapTo(t, Team.class), jerseyNumber);

	}

	public void changeJerseyNumber(PlayerDto p, TeamDto t, int jerseyNumber) {
		teamService.changeJerseyNumber(beanMappingService.mapTo(p, Player.class),
				beanMappingService.mapTo(t, Team.class), jerseyNumber);

	}

	public void removePlayerFromTeam(PlayerDto p, TeamDto t) {
		teamService.removePlayerFromTeam(beanMappingService.mapTo(p, Player.class),
				beanMappingService.mapTo(t, Team.class));
	}
}
