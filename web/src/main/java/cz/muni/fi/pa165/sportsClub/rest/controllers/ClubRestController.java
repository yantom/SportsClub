package cz.muni.fi.pa165.sportsClub.rest.controllers;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.facade.ClubFacade;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Koncak, 410155
 */
@RestController
@RequestMapping("/rest/club")
public class ClubRestController {
    
    @Inject
    ClubFacade clubFacade;
    
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ClubDto create(@Valid @RequestBody ClubDto club) {
        clubFacade.createClub(club);
        return club;
    }
    
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public void update(@Valid @RequestBody ClubDto club) {
        clubFacade.updateClub(club);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        clubFacade.deleteClub(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ClubDto findById(@PathVariable("id") long id) {
        return clubFacade.getClubById(id);
    }

    @RequestMapping(path = "/findall", method = RequestMethod.GET)
    public List<ClubDto> findAll() {
        return clubFacade.getAllClubs();
    }
    
    @RequestMapping(path = "/free/{id}", method = RequestMethod.GET)
    public List<PlayerDto> freePlayers(@PathVariable("id") long id) {
        return clubFacade.getFreePlayers(id);
    }
}
