package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import cz.muni.fi.pa165.sportsClub.service.PlayerService;
import cz.muni.fi.pa165.sportsClub.service.mappers.TeamOfPlayerMapper;

@Transactional
@Service
public class PlayerFacadeImpl implements PlayerFacade {

	@Inject
	PlayerService playerService;

	public void createPlayer(PlayerDto p) {
		Player playerEntity = new ModelMapper().map(p, Player.class);
		playerService.createPlayer(playerEntity);
		p.setId(playerEntity.getId());
	}

	public void updatePlayer(PlayerDto p) {
		playerService.updatePlayer(new ModelMapper().map(p, Player.class));
	}

	public void deletePlayer(PlayerDto p) {
		playerService.deletePlayer(new ModelMapper().map(p, Player.class));
	}

	public PlayerDto getPlayerById(Long playerId) {
		return new ModelMapper().map(playerService.getPlayerById(playerId), PlayerDto.class);
	}

	public PlayerDto getPlayerByEmail(String email) {
		return new ModelMapper().map(playerService.getPlayerByEmail(email), PlayerDto.class);
	}

	public List<TeamOfPlayerDto> getTeamsOfPlayer(PlayerDto p) {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new TeamOfPlayerMapper());
		List<TeamOfPlayerDto> teams = new ArrayList<TeamOfPlayerDto>();
		List<PlayerInfo> infos = playerService.getPlayerInfos(new ModelMapper().map(p, Player.class));
		for (PlayerInfo playerInfo : infos) {
			teams.add(mapper.map(playerInfo, TeamOfPlayerDto.class));
		}
		return teams;
	}

	public List<PlayerDto> getAllPlayersOfClub(ClubDto c) {
		return new ModelMapper().map(playerService.getAllPlayersOfClub(new ModelMapper().map(c, Club.class)),
				new TypeToken<List<TeamDto>>() {
				}.getType());
	}
	
	

}
