package cz.muni.fi.pa165.sportsClub.service.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerOfTeamDto;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;

public class PlayerOfTeamMapper extends PropertyMap<PlayerInfo, PlayerOfTeamDto> {
	protected void configure() {
		map().setJerseyNumber(source.getJerseyNumber());
		map().setPlayer(new ModelMapper().map(source.getPlayer(), PlayerDto.class));
	}
}
