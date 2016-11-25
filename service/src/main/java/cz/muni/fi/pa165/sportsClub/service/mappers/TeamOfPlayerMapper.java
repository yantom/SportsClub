package cz.muni.fi.pa165.sportsClub.service.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamOfPlayerDto;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;

public class TeamOfPlayerMapper extends PropertyMap<PlayerInfo, TeamOfPlayerDto> {
	protected void configure() {
		map().setJerseyNumber(source.getJerseyNumber());
		map().setTeam(new ModelMapper().map(source.getTeam(), TeamDto.class));
	  }
}