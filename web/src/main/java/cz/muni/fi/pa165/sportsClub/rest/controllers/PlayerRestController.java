package cz.muni.fi.pa165.sportsClub.rest.controllers;

import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.facade.PlayerFacade;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Koncak, 410155
 */
@RestController
@RequestMapping("/rest/player")
public class PlayerRestController {
    
    @Inject
    PlayerFacade playerFacade;
    
     @RequestMapping(path = "/create", method = RequestMethod.POST)
    public PlayerDto create(@Valid @RequestBody PlayerDto player) {
        playerFacade.createPlayer(player);
        return player;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public void update(@Valid @RequestBody PlayerDto player) {
        playerFacade.updatePlayer(player);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        playerFacade.deletePlayer(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PlayerDto findById(@PathVariable("id") long id) {
        return playerFacade.getPlayerById(id);
    }

    @RequestMapping(path = "/findall/{id}", method = RequestMethod.GET)
    public List<PlayerDto> findAll(@PathVariable("id") long id) {
        return playerFacade.getAllPlayersOfClub(id);
    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public PlayerDto findByEmail(@RequestParam("name") String email) {
        return playerFacade.getPlayerByEmail(email);
    }

}
