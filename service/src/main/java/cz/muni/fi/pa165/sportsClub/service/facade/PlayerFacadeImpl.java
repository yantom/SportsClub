package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamOfPlayerDto;
import cz.muni.fi.pa165.sportsClub.facade.PlayerFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.service.BeanMappingService;
import cz.muni.fi.pa165.sportsClub.service.PlayerService;

@Transactional
@Service
public class PlayerFacadeImpl implements PlayerFacade {

	@Inject
	PlayerService playerService;

	@Inject
	private BeanMappingService beanMappingService;

	public void createPlayer(PlayerDto p) {
		Player playerEntity = beanMappingService.mapTo(p, Player.class);
		playerService.createPlayer(playerEntity);
		p.setId(playerEntity.getId());
	}

	public void updatePlayer(PlayerDto p) {
		playerService.updatePlayer(beanMappingService.mapTo(p, Player.class));
	}

	public void deletePlayer(PlayerDto p) {
		playerService.deletePlayer(beanMappingService.mapTo(p, Player.class));
	}

	public PlayerDto getPlayerById(Long playerId) {
		Player p = playerService.getPlayerById(playerId);
		if (p == null)
			return null;
		PlayerDto pp = beanMappingService.mapTo(p, PlayerDto.class);
		return pp;
	}

	public PlayerDto getPlayerByEmail(String email) {
		Player p = playerService.getPlayerByEmail(email);
		if (p == null)
			return null;
		return beanMappingService.mapTo(p, PlayerDto.class);
	}

	public List<TeamOfPlayerDto> getTeamsOfPlayer(PlayerDto p) {
		List<TeamOfPlayerDto> teams = new ArrayList<TeamOfPlayerDto>();
		List<PlayerInfo> infos = playerService.getPlayerInfos(beanMappingService.mapTo(p, Player.class));
		TeamOfPlayerDto teamOfPlayer;
		for (PlayerInfo playerInfo : infos) {
			teamOfPlayer = new TeamOfPlayerDto();
			teamOfPlayer.setJerseyNumber(playerInfo.getJerseyNumber());
			teamOfPlayer.setPlayerOlderThanTeamLimit(playerInfo.isPlayerOlderThanTeamLimit());
			teamOfPlayer.setTeam(beanMappingService.mapTo(playerInfo.getTeam(), TeamDto.class));
			teams.add(teamOfPlayer);
		}
		return teams;
	}

	public List<PlayerDto> getAllPlayersOfClub(ClubDto c) {
		return beanMappingService.mapTo(playerService.getAllPlayersOfClub(beanMappingService.mapTo(c, Club.class)),
				PlayerDto.class);
	}
	
	

}
