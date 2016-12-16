package cz.muni.fi.pa165.sportsClub.rest.controllers;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.facade.ClubFacade;
import cz.muni.fi.pa165.sportsClub.facade.ManagerFacade;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping("/club")
public class ClubController {

    @Inject
    private ManagerFacade managerFacade;

    @Inject
    private ClubFacade clubFacade;

    @RequestMapping(path = "{managerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDto> getTeams(@PathVariable long managerId){
       return managerFacade.getManagerById(managerId).getClub().getTeams();
    }

    @RequestMapping(path = "{managerId}/freePlayers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDto> getFreePlayers(@PathVariable long managerId){
        ClubDto clubDto = managerFacade.getManagerById(managerId).getClub();
        return clubFacade.getFreePlayers(clubDto.getId());
    }



}
