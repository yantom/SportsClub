package cz.muni.fi.pa165.sportsClub.facade;

import java.util.List;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;

public interface ClubFacade {
	void createClub(ClubDto c);

	void updateClub(ClubDto c);

	void deleteClub(ClubDto c);

	ClubDto getClubById(Long clubId);

	ClubDto getClubByName(String clubName);

	void assignManagerToClub(ManagerDto m, ClubDto c);

	List<PlayerDto> getFreePlayers(ClubDto c);

	List<ClubDto> getAllClubs();
}
