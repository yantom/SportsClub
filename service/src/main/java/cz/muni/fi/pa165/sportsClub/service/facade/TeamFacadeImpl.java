package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public void createTeam(TeamDto t) {
		teamService.createTeam(beanMappingService.mapTo(t, Team.class));
	}

	@Override
	public void updateTeam(TeamDto t) {
		teamService.updateTeam(beanMappingService.mapTo(t, Team.class));

	}

	@Override
	public void deleteTeam(Long id) {
		teamService.deleteTeam(new Team(id));

	}

	@Override
	public TeamDto getTeamById(Long teamId) {
		Team t = teamService.getTeamById(teamId);
		return beanMappingService.mapTo(t, TeamDto.class);
	}

	@Override
	public TeamDto getTeamOfClubByCategory(Category category, Long clubId) {
		Team t = teamService.getTeamOfClubByCategory(category, new Club(clubId));
		if (t == null)
			return null;
		return beanMappingService.mapTo(t, TeamDto.class);
	}

	@Override
	public List<TeamDto> getAllTeamsOfClub(Long clubId) {
		return beanMappingService.mapTo(teamService.getAllTeamsOfClub(new Club(clubId)),
				TeamDto.class);
	}

	@Override
	public List<PlayerOfTeamDto> getPlayersOfTeam(Long teamId) {
		List<PlayerOfTeamDto> players = new ArrayList<PlayerOfTeamDto>();
		List<PlayerInfo> infos = teamService.getPlayerInfos(new Team(teamId));
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

	@Override
	public void assignExistingPlayerToTeam(Long pID, Long tID, int jerseyNumber) {
		teamService.assignExistingPlayerToTeam(new Player(pID), new Team(tID), jerseyNumber);
	}

	@Override
	public void assignNewPlayerToTeam(Long pID, Long tID, int jerseyNumber) {
		teamService.assignNewPlayerToTeam(new Player(pID), new Team(tID), jerseyNumber);
	}

	@Override
	public void changeJerseyNumber(Long pID, Long tID, int jerseyNumber) {
		teamService.changeJerseyNumber(new Player(pID), new Team(tID), jerseyNumber);

	}

	@Override
	public void removePlayerFromTeam(Long pID, Long tID) {
		teamService.removePlayerFromTeam(new Player(pID), new Team(tID));
	}
}
