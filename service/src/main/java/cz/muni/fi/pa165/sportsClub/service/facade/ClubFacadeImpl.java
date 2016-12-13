package cz.muni.fi.pa165.sportsClub.service.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.facade.ClubFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.service.BeanMappingService;
import cz.muni.fi.pa165.sportsClub.service.ClubService;

@Transactional
@Service
public class ClubFacadeImpl implements ClubFacade {

	@Inject
	private ClubService clubService;

	@Inject
	private BeanMappingService beanMappingService;

	@Override
	public void createClub(ClubDto c) {
		Club clubEntity = beanMappingService.mapTo(c, Club.class);
		clubService.createClub(clubEntity);
		c.setId(clubEntity.getId());
	}

	@Override
	public void updateClub(ClubDto c) {
		clubService.updateClub(beanMappingService.mapTo(c, Club.class));
	}

	@Override
	public void deleteClub(Long id) {
		clubService.deleteClub(new Club(id));
	}

	@Override
	public ClubDto getClubById(Long clubId) {
		Club c = clubService.getClubById(clubId);
		if (c == null)
			return null;
		return beanMappingService.mapTo(c, ClubDto.class);
	}

	@Override
	public ClubDto getClubByName(String clubName) {
		Club c = clubService.getClubByName(clubName);
		if (c == null)
			return null;
		return beanMappingService.mapTo(c, ClubDto.class);
	}

	@Override
	public void assignManagerToClub(ManagerDto m, Long clubId) {
		clubService.assignManagerToClub((beanMappingService.mapTo(m, Manager.class)), new Club(clubId));
	}

	@Override
	public List<ClubDto> getAllClubs() {
		return beanMappingService.mapTo(clubService.getAllClubs(), ClubDto.class);
	}

	@Override
	public List<PlayerDto> getFreePlayers(Long clubId) {
		return beanMappingService.mapTo(clubService.getFreePlayersOfClub(new Club(clubId)),
				PlayerDto.class);
	}
}
