package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.facade.ClubFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.service.ClubService;

@Transactional
@Service
public class ClubFacadeImpl implements ClubFacade {

	@Inject
	ClubService clubService;

	public void createClub(@Valid ClubDto c) {
		clubService.createClub(new ModelMapper().map(c, Club.class));
	}

	public void updateClub(ClubDto c) {
		clubService.updateClub(new ModelMapper().map(c, Club.class));
	}

	public void deleteClub(ClubDto c) {
		clubService.deleteClub(new ModelMapper().map(c, Club.class));
	}

	public ClubDto getClubById(Long clubId) {
		return new ModelMapper().map(clubService.getClubById(clubId), ClubDto.class);
	}

	public ClubDto getClubByName(String clubName) {
		return new ModelMapper().map(clubService.getClubByName(clubName), ClubDto.class);
	}

	public void assignManagerToClub(ManagerDto m, ClubDto c) {
		clubService.assignManagerToClub((new ModelMapper().map(m, Manager.class)),
				(new ModelMapper().map(c, Club.class)));
	}

	public List<ClubDto> getAllClubs() {
		return new ModelMapper().map(clubService.getAllClubs(), new TypeToken<List<ClubDto>>() {
		}.getType());
	}

	public List<PlayerDto> getFreePlayers() {
		throw new UnsupportedOperationException();
	}

}
