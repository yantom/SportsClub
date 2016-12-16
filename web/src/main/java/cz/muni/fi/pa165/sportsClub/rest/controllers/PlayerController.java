package cz.muni.fi.pa165.sportsClub.rest.controllers;

import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.facade.PlayerFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/player")
public class PlayerController {

    @Inject
    private PlayerFacade playerFacade;

    @RequestMapping(value = "/{playerId}", method = RequestMethod.DELETE)
    public final ResponseEntity deletePlayer(@PathVariable long playerId){
        try {
            playerFacade.deletePlayer(playerId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Player was successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public PlayerDto create(@Valid @RequestBody PlayerDto player) {
        playerFacade.createPlayer(player);
        return player;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public void update(@Valid @RequestBody PlayerDto player) {
        playerFacade.updatePlayer(player);
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
